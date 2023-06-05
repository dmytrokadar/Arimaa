package cz.cvut.fel.pjv.semestral.kadardmy.Utilities;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Cat;
import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Figure;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameRecorderTest {

    @BeforeEach
    void init(){
        GameRecorder gameRecorder = new GameRecorder();
    }

    @Test
    void intToString() {
        Assertions.assertEquals(GameRecorder.intToString(1), 'b');
    }

    @Test
    void intToPos() {
        Assertions.assertEquals(GameRecorder.intToPos(3), 5);
    }

    @Test
    void stringToInt() {
        Assertions.assertEquals(GameRecorder.stringToInt('d'), 3);
    }

    @Test
    void posToInt() {
        Assertions.assertEquals(GameRecorder.posToInt(8), 0);
    }

    @Test
    void stringToColor() {
        Assertions.assertEquals(GameRecorder.stringToColor("SILVER"), Board.Color.SILVER);
    }

    @Test
    void stringToFigure() {
        Figure figure = GameRecorder.stringToFigure('C', 0, 0);
        Cat test = new Cat(Board.Color.GOLD, 0, 0);

        Assertions.assertEquals(figure.getStrength(), test.getStrength());
    }
}