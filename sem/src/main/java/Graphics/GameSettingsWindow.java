package Graphics;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameSettingsWindow extends Scene{
    /*
    * Setup gamemode(vs computer or versus other player), choose name
    * */

    public GameSettingsWindow(){
        super(new Pane());
        Scene scene = new GameScene(false);
    }
}
