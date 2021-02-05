package game;

import game.fields.GpsCoordinate;
import game.fields.UndiscoveredTerritory;
import game.fields.UnreachableTerritory;
import game.fields.YellowPoint;
import game.fields.abstraction.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class GameBoard extends JFrame implements MouseListener {

    private GameField[][] fields = new GameField[8][8];
    private GameField chosenField;

    public GameBoard() throws HeadlessException {
        super("GPS-a ми се счупи");
        super.addMouseListener(this);

    }

    /*
    start of the game
     */
    public void start() {
        generateFields();
        initWindow();
    }

    /*
    restart of the game
     */
    private void restart() {
        this.fields = new GameField[8][8];
        this.start();
        super.repaint();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (GameField[] field : fields) {
            for (GameField gameField : field) {
                if (gameField != null) {
                    gameField.render(g);
                }
            }
        }

    }

    /*
    initialization of the window
     */
    private void initWindow() {
        super.setSize(800, 800);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
    generation of fields
     */
    private void generateFields() {
        Random random = new Random();
        generateStartingPoint(random);
        try {
            generateSpecificTerritory(8, GpsCoordinate.class, random);
            generateSpecificTerritory(5, UnreachableTerritory.class, random);
            generateSpecificTerritory(50, UndiscoveredTerritory.class, random);

        } catch (Exception ignored) {

        }

    }


    /**
     * Method that geenrate specific figures
     * @param availableFigures number of the figures to be printed
     * @param clazz class of the figures
     * @param random instance of Random
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */

    private void generateSpecificTerritory(int availableFigures, Class<?> clazz, Random random) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int row;
        int col;

        Constructor<?> constructor = clazz.getDeclaredConstructor(int.class, int.class);

        while (availableFigures != 0) {
            row = random.nextInt(8);
            col = random.nextInt(8);

            if (fields[row][col] != null) {
                continue;
            }

            fields[row][col] = (GameField) constructor.newInstance(row, col);

            availableFigures--;
        }
    }


    /**
     * generation of starting point on random place
     * @param random random instance
     */
    private void generateStartingPoint(Random random) {
        int[] randomPositionXY = {0, 7};
        int row = randomPositionXY[random.nextInt(2)];
        int col = randomPositionXY[random.nextInt(2)];

        fields[row][col] = new YellowPoint(row, col, "");

    }

    /**
     * method that handle user click and move figures
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY() / GameField.FIELD_SIZE;
        int col = e.getX() / GameField.FIELD_SIZE;

        GameField clickedField=fields[row][col];


        if (isMoveValid(row, col)) {

            if(chosenField!=null){

                if (!chosenField.equals(clickedField)) {
                    return;
                }
                fields[row][col] = fieldGenerator(row, col);
                chosenField = null;

                if(isEndgameConditionOccurred(row,col)){
                    visualiseEndingWindow("Ти загуби");
                }
            } else {
                if (clickedField instanceof GpsCoordinate gpsCoordinate) {
                    if (gpsCoordinate.isEnding()) {
                        visualiseEndingWindow("Ти спечели!!!");
                    }
                }
                chosenField = new YellowPoint(row, col, "?");
                fields[row][col] = chosenField;

            }

        }

        super.repaint();
    }

    /**
     * visualisation of the ending window
     * @param title
     */
    private void visualiseEndingWindow(String title) {
        FinishDialog finishDialog = new FinishDialog(this, true, title, (action) -> {
            restart();

        }
                , (action) -> System.exit(0));

        finishDialog.visualize();
    }

    /**
     * field generator that generate UnreachableTerritory(20%) or YellowPoint(80%)
     * @param row row of the matrix
     * @param col col of the matrix
     * @return
     */
    private GameField fieldGenerator(int row, int col) {
        Random random = new Random();
        int randomN = random.nextInt(10);


        return randomN < 2 ? new UnreachableTerritory(row, col) : new YellowPoint(row, col, "");
    }


    /**
     * check for game ending
     * @param row
     * @param col
     * @return
     */
    private boolean isEndgameConditionOccurred(int row, int col) {
        Point[] surroundingCoordinates = generateSurroundingCoordinates(row, col);

        for (Point point : surroundingCoordinates) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            try {
                if (!(fields[y][x] instanceof UnreachableTerritory)) {
                    return false;
                }
            } catch (Exception ignored) {

            }
        }
        return true;

    }

    /**
     * check if move is valid
     * @param row row in the matrix
     * @param col col in the matrix
     * @return
     */
    private boolean isMoveValid(int row, int col) {

        Point[] surroundingCoordinates = generateSurroundingCoordinates(row, col);

        for (Point point : surroundingCoordinates) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            try {
                if (fields[y][x] instanceof YellowPoint) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("out of bound");
            }
        }

        return false;
    }

    /**
     * generator of surrounding points
     * @param row row in the matrix
     * @param col col in the matrix
     * @return Array of Points
     */
    private Point[] generateSurroundingCoordinates(int row, int col) {
        Point upper = new Point(col, row - 1);
        Point lower = new Point(col, row + 1);
        Point right = new Point(col + 1, row);
        Point left = new Point(col - 1, row);

        return new Point[]{upper, lower, right, left};
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
