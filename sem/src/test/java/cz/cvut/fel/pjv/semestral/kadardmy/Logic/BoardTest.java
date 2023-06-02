package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
    Board board;

    @BeforeEach
    void init(){
        board = new Board();
        board.setPhase(Board.Phase.GAME);
    }

    @Test
    void increaseMoveCountOnce(){
        int count = board.getMoveCount();
        board.increaseMoveCount();

        Assertions.assertEquals(board.getMoveCount(), count+1);
    }

    @org.junit.jupiter.api.Test
    void increaseMoveCount() {
        Board.Color color = board.getCurrentColorMove();

        for(int i = 0; i < 4; i++)
            board.increaseMoveCount();

        Assertions.assertNotEquals(board.getCurrentColorMove(), color);
    }
}