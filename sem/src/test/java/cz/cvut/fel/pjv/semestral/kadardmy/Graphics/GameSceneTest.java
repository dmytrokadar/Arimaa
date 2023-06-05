package cz.cvut.fel.pjv.semestral.kadardmy.Graphics;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Rabbit;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;
import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSceneTest {
    GameScene gameScene;

    @BeforeEach
    void init(){
        gameScene = new GameScene(false, false, false);
    }


    @Test
    void checkForWinGold() {
        gameScene.tiles[0][0].removeFigure();
        gameScene.tiles[0][0].setFigureView(new FigureView(new Image("Textures/rabbit_g.png"),
                0, 0, new Rabbit(Board.Color.GOLD)));

        Assertions.assertEquals(gameScene.checkForWin(), true);
    }

    @Test
    void friendlyFigureNear() {
        Assertions.assertEquals(gameScene.friendlyFigureNear(gameScene.tiles[0][0].getFigureView()), true);
    }

    @Test
    void testFriendlyFigureNear() {
        Assertions.assertEquals(gameScene.friendlyFigureNear(gameScene.tiles[0][0].getFigureView(), Board.Color.GOLD), false);
    }
}