package game.fields.abstraction;

import java.awt.*;

public abstract class GameField {
    protected int y;
    protected int x;
    protected Color color;
    public static final int FIELD_SIZE = 100;

    public GameField(int y, int x, Color color) {
        setX(x);
        setY(y);
        this.color = color;
    }

    public void render(Graphics g) {
        fill(g);
        draw(g);
    }

    private void fill(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, GameField.FIELD_SIZE, GameField.FIELD_SIZE);
    }

    private void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, GameField.FIELD_SIZE, GameField.FIELD_SIZE);
    }

    public void setY(int y) {
        this.y = y * GameField.FIELD_SIZE;
    }

    public void setX(int x) {
        this.x = x * GameField.FIELD_SIZE;
    }
}
