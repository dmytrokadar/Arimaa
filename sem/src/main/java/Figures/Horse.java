package Figures;

public class Horse extends Figure{
    public Horse(String color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.HORSE;
    }

    public Horse(String color) {
        super(color);
        type = TYPE.HORSE;
    }
}
