package Figures;

import javax.swing.text.Position;

public abstract class Figure {
    private String color;
    private int positionX;
    private int positionY;

    public Figure(String color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
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
}
