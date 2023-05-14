package Figures;

import Logic.Board;

public class Horse extends Figure{
    public Horse(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.HORSE;
    }

    public Horse(Board.Color color) {
        super(color);
        type = TYPE.HORSE;
    }
}
