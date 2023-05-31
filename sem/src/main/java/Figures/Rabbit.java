package Figures;

import Logic.Board;

public class Rabbit extends Figure{
    public Rabbit(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.RABBIT;
        strength = STRENGTH.RABBIT;
    }

    public Rabbit(Board.Color color) {
        super(color);
        type = TYPE.RABBIT;
        strength = STRENGTH.RABBIT;
    }
}
