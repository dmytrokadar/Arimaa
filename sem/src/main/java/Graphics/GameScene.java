package Graphics;

import Logic.Board;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {

    public GameScene() {
        super(new Pane());
    }

    public void GameScene(){
        Board b = new Board();
    }
}
