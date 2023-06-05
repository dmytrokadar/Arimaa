package cz.cvut.fel.pjv.semestral.kadardmy.Figures;

import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

public class Horse extends Figure{
    /**
     * Constructor for Horse
     *
     * @param color - golden or silver
     * @param positionX - position of the figure on the board
     * @param positionY - position of the figure on the board
     * */
    public Horse(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.HORSE;
        strength = STRENGTH.HORSE;
        this.color = color;
        isFrozen = false;

        if(color == Board.Color.GOLD)
            imagePath = "Textures/horse_g.png";
        else
            imagePath = "Textures/horse_s.png";
    }

    /**
     * Constructor for Horse
     *
     * @param color - golden or silver
     * */
    public Horse(Board.Color color) {
        super(color);
        type = TYPE.HORSE;
        strength = STRENGTH.HORSE;
        this.color = color;
        isFrozen = false;

        if(color == Board.Color.GOLD)
            imagePath = "Textures/horse_g.png";
        else
            imagePath = "Textures/horse_s.png";
    }

    @Override
    public String toString() {
        if(color == Board.Color.GOLD){
            return "H";
        }
        return "h";
    }
}
