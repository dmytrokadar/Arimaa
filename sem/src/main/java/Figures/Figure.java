package Figures;

import Logic.Board;

import javax.swing.text.Position;

public abstract class Figure {

    /*
    * Abstract class for all figures
    * */

    public interface STRENGTH {
        int RABBIT = 0;
        int CAT = 1;
        int DOG = 1;
        int HORSE = 2;
        int CAMEL = 2;
        int ELEPHANT = 3;
    }

    public enum TYPE {
        RABBIT,
        CAT,
        DOG,
        HORSE,
        CAMEL,
        ELEPHANT
    }

    private Board.Color color;
    private int positionX;
    private int positionY;
    protected TYPE type;
    private int strength;
    private boolean isFrozen;

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
