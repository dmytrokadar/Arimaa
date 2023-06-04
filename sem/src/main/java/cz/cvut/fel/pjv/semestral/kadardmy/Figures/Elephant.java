package cz.cvut.fel.pjv.semestral.kadardmy.Figures;

import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

public class Elephant extends Figure{
    /**
     * Constructor for Elephant
     *
     * @param color - golden or silver
     * @param positionX - position of the figure on the board
     * @param positionY - position of the figure on the board
     * */
    public Elephant(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.ELEPHANT;
        strength = STRENGTH.ELEPHANT;
        this.color = color;
        isFrozen = false;
    }

    /**
     * Constructor for Elephant
     *
     * @param color - golden or silver
     * */
    public Elephant(Board.Color color) {
        super(color);
        type = TYPE.ELEPHANT;
        strength = STRENGTH.ELEPHANT;
        this.color = color;
        isFrozen = false;
    }

    @Override
    public String toString() {
        if(color == Board.Color.GOLD){
            return "E";
        }
        return "e";
    }
}
