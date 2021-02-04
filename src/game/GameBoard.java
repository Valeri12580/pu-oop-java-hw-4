package game;

import game.fields.GpsCoordinate;
import game.fields.StartingPoint;
import game.fields.UndiscoveredTerritory;
import game.fields.UnreachableTerritory;
import game.fields.abstraction.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class GameBoard extends JFrame implements MouseListener {

    //todo Block other fields when the user chose field with ? , show proper field(blue or yellow) , change Starting Point class name


    private GameField[][] fields = new GameField[8][8];
    private GameField chosenField;

    public GameBoard() throws HeadlessException {
        super("GPS-a ми се счупи");
        super.addMouseListener(this);
        initWindow();
        generateFields();
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

    private void initWindow() {
        super.setSize(800, 800);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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


    private void generateSpecificTerritory(int availableFigures ,Class<?> clazz, Random random) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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


    private void generateStartingPoint(Random random) {
        int[] randomPositionXY = {0, 7};
        int row = randomPositionXY[random.nextInt(2)];
        int col = randomPositionXY[random.nextInt(2)];

        fields[row][col] = new StartingPoint(row, col, "");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY() / GameField.FIELD_SIZE;
        int col = e.getX() / GameField.FIELD_SIZE;

        if (chosenField == null) {
            if (isMoveValid(row, col)) {
                chosenField = new StartingPoint(row, col, "?");
                fields[row][col] = chosenField;
            }
        } else {
            if (!chosenField.equals(fields[row][col])) {
                return;
            }

            fields[row][col] = fieldGenerator(row, col);
            chosenField = null;
        }


        super.repaint();


    }

    private GameField fieldGenerator(int row, int col) {
        Random random = new Random();
        int randomN = random.nextInt(10);


        return randomN < 2 ? new UnreachableTerritory(row,col):new StartingPoint(row,col,"");
    }

    private boolean isMoveValid(int row, int col) {

        Point upper = new Point(col, row - 1);
        Point lower = new Point(col, row + 1);
        Point right = new Point(col + 1, row);
        Point left = new Point(col - 1, row);

        Point[] surroundingCoordinates = new Point[]{upper, lower, right, left};

        for (Point point : surroundingCoordinates) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            try {
                if (fields[y][x] instanceof StartingPoint) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("out of bound");
            }
        }

        return false;
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
