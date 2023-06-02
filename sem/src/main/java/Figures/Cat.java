package Figures;

import Logic.Board;

public class Cat extends Figure{
    /**
     * Constructor for Cat
     *
     * @param color - golden or silver
     * @param positionX - position of the figure on the board
     * @param positionY - position of the figure on the board
     * */
    public Cat(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAT;
        strength = STRENGTH.CAT;
        this.color = color;
        isFrozen = false;
    }

    /**
     * Constructor for Cat
     *
     * @param color - golden or silver
     * */
    public Cat(Board.Color color) {
        super(color);
        type = TYPE.CAT;
        strength = STRENGTH.CAT;
        this.color = color;
        isFrozen = false;
    }
}
