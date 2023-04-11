package Figures;

import javax.swing.text.Position;

public abstract class Figure {

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

    private String color;
    private int positionX;
    private int positionY;
    protected TYPE type;
    private int strength;
    private boolean isFrozen;

    public Figure(String color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        isFrozen = false;
    }

    public Figure(String color) {
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

    public String getColor() {
        return color;
    }

    public boolean isFrozen() {
        return isFrozen;
    }
}
