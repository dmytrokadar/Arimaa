package Figures;

import Logic.Board;

public class Cat extends Figure{
    public Cat(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAT;
        strength = STRENGTH.CAT;
        this.color = color;
        isFrozen = false;
    }

    public Cat(Board.Color color) {
        super(color);
        type = TYPE.CAT;
        strength = STRENGTH.CAT;
        this.color = color;
        isFrozen = false;
    }
}
