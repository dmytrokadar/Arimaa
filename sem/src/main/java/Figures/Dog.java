package Figures;

public class Dog extends Figure{
    public Dog(String color, int positionX, int positionY) {
        super(color, positionX, positionY);
        type = TYPE.DOG;
    }

    public Dog(String color) {
        super(color);
        type = TYPE.DOG;
    }
}
