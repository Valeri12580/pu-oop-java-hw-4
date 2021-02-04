package game.fields;

import game.fields.abstraction.GameField;

import java.awt.*;

public class UnreachableTerritory  extends GameField {
    public UnreachableTerritory(int y, int x) {
        super(y, x, Color.BLUE);
    }
}
