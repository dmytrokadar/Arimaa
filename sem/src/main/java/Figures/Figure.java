package Figures;

import Logic.Board;

import javax.swing.text.Position;

public abstract class Figure {

    /*
    * Abstract class for all figures
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

        private int getValue(){
            return value;
        }
    }

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

    public Figure(Board.Color color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        isFrozen = false;
    }

    public Figure(Board.Color color) {
        this.color = color;
    }

    public void changePosition(int positionX, int positionY) {
        //TODO mb provirku i ne treba
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
}
