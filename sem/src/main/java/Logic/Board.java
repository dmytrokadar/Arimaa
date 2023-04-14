package Logic;

import Utilities.GameRecorder;
import Utilities.Timer;

public class Board {
    public enum Color{
        GOLD,
        SILVER
    }

    public void Board(){
        Player p = new Player();
        AI ai = new AI();
        GameRecorder gameRecorder = new GameRecorder();
        Timer timer = new Timer();
    }


}
