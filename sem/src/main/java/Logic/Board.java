package Logic;

import Figures.Camel;
import Utilities.GameRecorder;
import Utilities.Timer;

public class Board {
    public enum Color{
        GOLD,
        SILVER
    }

    public Board(){
        Player p = new Player();
        AI ai = new AI();
        GameRecorder gameRecorder = new GameRecorder();
        Timer timer = new Timer();

        // init figures
        Camel camelGold = new Camel(Color.GOLD);
    }


}
