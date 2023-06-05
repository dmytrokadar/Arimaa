package cz.cvut.fel.pjv.semestral.kadardmy.Figures;

import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

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

        if(color == Board.Color.GOLD)
            imagePath = "Textures/cat_g.png";
        else
            imagePath = "Textures/cat_s.png";
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

        if(color == Board.Color.GOLD)
            imagePath = "Textures/cat_g.png";
        else
            imagePath = "Textures/cat_s.png";
    }

    @Override
    public String toString() {
        if(color == Board.Color.GOLD){
            return "C";
        }
        return "c";
    }
}
