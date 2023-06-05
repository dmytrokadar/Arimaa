package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Figure;

public class Move {
    private final Figure figure;
    private final int posXFrom;
    private final int posYFrom;
    private final int posXTo;
    private final int posYTo;
    private final Board.Color color;
    private final int moveCount;

    public Move(Figure figure, int posXFrom, int posYFrom, int posXTo, int posYTo, Board.Color color, int moveCount) {
        this.figure = figure;
        this.posXFrom = posXFrom;
        this.posYFrom = posYFrom;
        this.posXTo = posXTo;
        this.posYTo = posYTo;
        this.color = color;
        this.moveCount = moveCount;
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

    public Figure getFigure() {
        return figure;
    }

    public Board.Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
