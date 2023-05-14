package Figures;

import Logic.Board;

public class Cat extends Figure{
    public Cat(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAT;
    }

    public Cat(Board.Color color) {
        super(color);
        type = TYPE.CAT;
    }
}
