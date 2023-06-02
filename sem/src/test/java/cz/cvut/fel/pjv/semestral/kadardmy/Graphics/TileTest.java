package cz.cvut.fel.pjv.semestral.kadardmy.Graphics;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Rabbit;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cz.cvut.fel.pjv.semestral.kadardmy.MainWindow.MainWindow.TILE_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    Tile tile;

    @BeforeEach
    void init(){
        tile = new Tile( new Rectangle(TILE_SIZE, TILE_SIZE, Color.WHITE), 0, 0);
        tile.setFigureView(new FigureView(new Image("Textures/rabbit_g.png"),
                0, 0, new Rabbit(Board.Color.GOLD)));
    }

    @Test
    void setFigureView() {
        Assertions.assertNotEquals(tile.getFigureView(), null);
    }

    @Test
    void removeFigure() {
        tile.removeFigure();
        Assertions.assertEquals(tile.getFigureView(), null);
    }
}