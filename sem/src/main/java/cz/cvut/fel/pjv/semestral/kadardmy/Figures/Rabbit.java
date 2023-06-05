package cz.cvut.fel.pjv.semestral.kadardmy.Figures;

import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

public class Rabbit extends Figure{
    /**
     * Constructor for Rabbit
     *
     * @param color - golden or silver
     * @param positionX - position of the figure on the board
     * @param positionY - position of the figure on the board
     * */
    public Rabbit(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.RABBIT;
        strength = STRENGTH.RABBIT;
        this.color = color;
        isFrozen = false;

        if(color == Board.Color.GOLD)
            imagePath = "Textures/rabbit_g.png";
        else
            imagePath = "Textures/rabbit_s.png";
    }

    /**
     * Constructor for Rabbit
     *
     * @param color - golden or silver
     * */
    public Rabbit(Board.Color color) {
        super(color);
        type = TYPE.RABBIT;
        strength = STRENGTH.RABBIT;
        this.color = color;
        isFrozen = false;

        if(color == Board.Color.GOLD)
            imagePath = "Textures/rabbit_g.png";
        else
            imagePath = "Textures/rabbit_s.png";
    }

    @Override
    public String toString() {
        if(color == Board.Color.GOLD){
            return "R";
        }
        return "r";
    }
}
