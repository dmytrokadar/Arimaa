package cz.cvut.fel.pjv.semestral.kadardmy.Figures;

import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

public class Dog extends Figure{
    /**
     * Constructor for Dog
     *
     * @param color - golden or silver
     * @param positionX - position of the figure on the board
     * @param positionY - position of the figure on the board
     * */
    public Dog(Board.Color color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.DOG;
        strength = STRENGTH.DOG;
        this.color = color;
        isFrozen = false;

        if(color == Board.Color.GOLD)
            imagePath = "Textures/dog_g.png";
        else
            imagePath = "Textures/dog_s.png";
    }

    /**
     * Constructor for Dog
     *
     * @param color - golden or silver
     * */
    public Dog(Board.Color color) {
        super(color);
        type = TYPE.DOG;
        strength = STRENGTH.DOG;
        this.color = color;
        isFrozen = false;

        if(color == Board.Color.GOLD)
            imagePath = "Textures/dog_g.png";
        else
            imagePath = "Textures/dog_s.png";
    }

    @Override
    public String toString() {
        if(color == Board.Color.GOLD){
            return "D";
        }
        return "d";
    }
}
