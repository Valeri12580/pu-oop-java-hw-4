package game.fields;

import game.fields.abstraction.GameField;

import java.awt.*;

public class StartingPoint extends GameField {
    private String symbol;
    private final int positionOfString=GameField.FIELD_SIZE/2;
    public StartingPoint(int y, int x, Color color, String symbol) {
        super(y, x, color);
        this.symbol = symbol;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawString(symbol,this.x+positionOfString,this.y+ positionOfString);
    }
}
