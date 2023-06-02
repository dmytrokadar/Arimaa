package Figures;

import Logic.Board;

public class Dog extends Figure{
    public Dog(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.DOG;
        strength = STRENGTH.DOG;
        this.color = color;
        isFrozen = false;
    }

    public Dog(Board.Color color) {
        super(color);
        type = TYPE.DOG;
        strength = STRENGTH.DOG;
        this.color = color;
        isFrozen = false;
    }
}
