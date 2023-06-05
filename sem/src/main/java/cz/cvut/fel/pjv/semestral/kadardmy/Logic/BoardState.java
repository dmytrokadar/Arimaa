package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

public class BoardState {
    private String moves[];
    private long time1;
    private long time2;
    private Board.Color color;

    public BoardState(String[] moves, long time1, long time2, Board.Color color) {
        this.moves = moves;
        this.time1 = time1;
        this.time2 = time2;
        this.color = color;
    }

    public String[] getMoves() {
        return moves;
    }

    public long getTime1() {
        return time1;
    }

    public long getTime2() {
        return time2;
    }

    public Board.Color getColor() {
        return color;
    }
}
