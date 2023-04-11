package Figures;

public class Elephant extends Figure{
    public Elephant(String color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.ELEPHANT;
    }

    public Elephant(String color) {
        super(color);
        type = TYPE.ELEPHANT;
    }
}
