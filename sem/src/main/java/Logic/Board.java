package Logic;

import Figures.*;
import Graphics.Tile;
import Utilities.GameRecorder;
import Utilities.Timer;

public class Board {
    public enum Color{
        GOLD,
        SILVER
    }

    public enum Phase{
        EDIT,
        GAME,
        END
    }

    public Board(){
        Player p = new Player();
        AI ai = new AI();
        GameRecorder gameRecorder = new GameRecorder();
        Timer timer = new Timer();

        // init figures
        // first phase will be as in original game client - figures are already on board,
        // but you can change their position
        Elephant elephantGold = new Elephant(Color.GOLD);

        Elephant elephantSilver = new Elephant(Color.SILVER);

        Camel camelGold = new Camel(Color.GOLD);
        Camel camelSilver = new Camel(Color.SILVER);

        Horse horseGold1 = new Horse(Color.GOLD);
        Horse horseGold2 = new Horse(Color.GOLD);
        Horse horseSilver1 = new Horse(Color.SILVER);
        Horse horseSilver2 = new Horse(Color.SILVER);

        Dog dogGold1 = new Dog(Color.GOLD);
        Dog dogGold2 = new Dog(Color.GOLD);
        Dog dogSilver1 = new Dog(Color.SILVER);
        Dog dogSilver2 = new Dog(Color.SILVER);

        Cat catGold1 = new Cat(Color.GOLD);
        Cat catGold2 = new Cat(Color.GOLD);
        Cat catSilver1 = new Cat(Color.SILVER);
        Cat catSilver2 = new Cat(Color.SILVER);

        Rabbit rabbitGold1 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold2 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold3 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold4 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold5 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold6 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold7 = new Rabbit(Color.GOLD);
        Rabbit rabbitGold8 = new Rabbit(Color.GOLD);
        Rabbit rabbitSilver1 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver2 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver3 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver4 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver5 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver6 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver7 = new Rabbit(Color.SILVER);
        Rabbit rabbitSilver8 = new Rabbit(Color.SILVER);
    }


}
