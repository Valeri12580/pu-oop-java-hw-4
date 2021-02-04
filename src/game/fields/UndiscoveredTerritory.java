package game.fields;

import game.fields.abstraction.GameField;

import java.awt.*;

public class UndiscoveredTerritory extends GameField {

    public UndiscoveredTerritory(int y, int x) {
        super(y, x, Color.RED);
    }
}
