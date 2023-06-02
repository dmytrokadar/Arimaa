package Figures;

import Logic.Board;

public class Camel extends Figure{
    /**
     * Constructor for Camel
     *
     * @param color - golden or silver
     * @param positionX - position of the figure on the board
     * @param positionY - position of the figure on the board
     * */
    public Camel(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAMEL;
        strength = STRENGTH.CAMEL;
        this.color = color;
        isFrozen = false;
    }

    /**
     * Constructor for Cat
     *
     * @param color - golden or silver
     * */
    public Camel(Board.Color color) {
        super(color);
        type = TYPE.CAMEL;
        strength = STRENGTH.CAMEL;
        this.color = color;
        isFrozen = false;
    }
}
