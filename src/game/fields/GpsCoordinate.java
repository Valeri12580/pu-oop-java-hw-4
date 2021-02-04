package game.fields;

import game.fields.abstraction.GameField;

import java.awt.*;

public class GpsCoordinate extends GameField {
    private boolean isEnding;
    private static boolean isBabaQgaAlreadyExist=false;

    public GpsCoordinate(int y, int x) {
        super(y, x, Color.GREEN);
        this.isEnding=true;
//        if(!isBabaQgaAlreadyExist){
//            isEnding=true;
//            isBabaQgaAlreadyExist=true;
//        }

    }

    public GpsCoordinate setEnding(boolean ending) {
        isEnding = ending;
        return this;
    }

    public boolean isEnding() {
        return isEnding;
    }
}
