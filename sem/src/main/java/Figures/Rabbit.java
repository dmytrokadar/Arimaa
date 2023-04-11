package Figures;

public class Rabbit extends Figure{
    public Rabbit(String color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.RABBIT;
    }

    public Rabbit(String color) {
        super(color);
        type = TYPE.RABBIT;
    }
}
