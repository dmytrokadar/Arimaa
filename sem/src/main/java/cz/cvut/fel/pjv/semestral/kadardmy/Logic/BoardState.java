package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

public class BoardState {
    private String moves[];
    private int time1;
    private int time2;
    private Board.Color color;

    public BoardState(String[] moves, int time1, int time2, Board.Color color) {
        this.moves = moves;
        this.time1 = time1;
        this.time2 = time2;
        this.color = color;
    }

    public String[] getMoves() {
        return moves;
    }

    public int getTime1() {
        return time1;
    }

    public int getTime2() {
        return time2;
    }

    public Board.Color getColor() {
        return color;
    }
}
