package Figures;

public class Camel extends Figure{
    public Camel(String color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAMEL;
    }

    public Camel(String color) {
        super(color);
        type = TYPE.CAMEL;
    }
}
