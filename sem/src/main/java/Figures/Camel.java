package Figures;

import Logic.Board;

public class Camel extends Figure{
    public Camel(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAMEL;
        strength = STRENGTH.CAMEL;
    }

    public Camel(Board.Color color) {
        super(color);
        type = TYPE.CAMEL;
        strength = STRENGTH.CAMEL;
    }
}
