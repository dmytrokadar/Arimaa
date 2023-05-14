package Figures;

import Logic.Board;

public class Elephant extends Figure{
    public Elephant(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.ELEPHANT;
    }

    public Elephant(Board.Color color) {
        super(color);
        type = TYPE.ELEPHANT;
    }
}
