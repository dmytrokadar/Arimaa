package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

public class BoardState {
    private String moves[];
    private long time1;
    private long time2;
    private Board.Color color;

    /**
     * State of the board to save and load
     *
     * @param moves - array of figures positions
     * @param time1 - time for Gold player
     * @param time2 - time for Silver player
     * @param color - color of current move
     * */
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
