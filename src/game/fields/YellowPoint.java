package game.fields;

import game.fields.abstraction.GameField;

import java.awt.*;

public class YellowPoint extends GameField {
    private String symbol;
    private final int positionOfString=GameField.FIELD_SIZE/2;

    public YellowPoint(int y, int x, String symbol) {
        super(y, x, Color.YELLOW);
        this.symbol = symbol;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawString(symbol,this.x+positionOfString,this.y+ positionOfString);
    }
}
