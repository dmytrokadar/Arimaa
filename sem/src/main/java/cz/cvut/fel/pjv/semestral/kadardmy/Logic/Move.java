package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

public class Move {
    private int posXFrom;
    private int posYFrom;
    private int posXTo;
    private int posYTo;

    public Move(int posXFrom, int posYFrom, int posXTo, int posYTo) {
        this.posXFrom = posXFrom;
        this.posYFrom = posYFrom;
        this.posXTo = posXTo;
        this.posYTo = posYTo;
    }

    public int getPosXFrom() {
        return posXFrom;
    }

    public int getPosYFrom() {
        return posYFrom;
    }

    public int getPosXTo() {
        return posXTo;
    }

    public int getPosYTo() {
        return posYTo;
    }
}
