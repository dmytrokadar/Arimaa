package cz.cvut.fel.pjv.semestral.kadardmy.Figures;

import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

public abstract class Figure {

    /**
    * Abstract class for all figures
    *
    * */
    public enum STRENGTH {
        RABBIT(0),
        CAT(1),
        DOG(1),
        HORSE(2),
        CAMEL(2),
        ELEPHANT(3);

        private final int value;

        STRENGTH(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    /**
    * Type of figure
    * */
    public enum TYPE {
        RABBIT,
        CAT,
        DOG,
        HORSE,
        CAMEL,
        ELEPHANT
    }

    protected Board.Color color;
    private int positionX;
    private int positionY;
    protected static TYPE type;
    protected STRENGTH strength;
    protected boolean isFrozen;

    /**
    * Constructor for cz.cvut.fel.pjv.semestral.kadardmy.Figures
    *
    * @param color - golden or silver
    * @param positionX - position of the figure on the board
    * @param positionY - position of the figure on the board
    * */
    public Figure(Board.Color color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        isFrozen = false;
    }

    /**
     * Constructor for cz.cvut.fel.pjv.semestral.kadardmy.Figures
     *
     * @param color - golden or silver
     * */
    public Figure(Board.Color color) {
        this.color = color;
    }

    /**
    * Change figure position
    * */
    public void changePosition(int positionX, int positionY) {
        if((positionX >= 0 && positionX < 8) && (positionY >= 0 && positionY < 8)){
            this.positionX = positionX;
            this.positionY = positionY;
            System.out.println("Done changing: " + positionX + " " + positionY);
        } else {
            System.out.println("Out of boundaries: " + positionX + " " + positionY);
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Board.Color getColor() {
        return color;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public STRENGTH getStrength() {
        return strength;
    }
}
