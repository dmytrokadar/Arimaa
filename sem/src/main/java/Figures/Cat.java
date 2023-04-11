package Figures;

public class Cat extends Figure{
    public Cat(String color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.CAT;
    }

    public Cat(String color) {
        super(color);
        type = TYPE.CAT;
    }
}
