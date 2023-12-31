package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.Tile;
import cz.cvut.fel.pjv.semestral.kadardmy.Utilities.GameRecorder;
import cz.cvut.fel.pjv.semestral.kadardmy.Utilities.Timer;

import java.util.logging.Logger;

public class Board {
    /**
     * Game logic
     *
     * */
    public enum Color{
        GOLD,
        SILVER
    }

    public enum Phase{
        EDIT,
        GAME,
        END
    }

    private Phase phase;
    private int moveCount = 0;
    private Color currentColorMove = Color.GOLD;
    public static Logger logger = Logger.getLogger(Board.class.getName());
    private GameRecorder gameRecorder;

    public Board(){
        Player p1 = new Player();
        AI ai = new AI();
        gameRecorder = new GameRecorder();

        phase = Phase.EDIT;
        // init figures
        // first phase will be as in original game client - figures are already on board,
        // but you can change their position
//        Elephant elephantGold = new Elephant(Color.GOLD);
//
//        Elephant elephantSilver = new Elephant(Color.SILVER);
//
//        Camel camelGold = new Camel(Color.GOLD);
//        Camel camelSilver = new Camel(Color.SILVER);
//
//        Horse horseGold1 = new Horse(Color.GOLD);
//        Horse horseGold2 = new Horse(Color.GOLD);
//        Horse horseSilver1 = new Horse(Color.SILVER);
//        Horse horseSilver2 = new Horse(Color.SILVER);
//
//        Dog dogGold1 = new Dog(Color.GOLD);
//        Dog dogGold2 = new Dog(Color.GOLD);
//        Dog dogSilver1 = new Dog(Color.SILVER);
//        Dog dogSilver2 = new Dog(Color.SILVER);
//
//        Cat catGold1 = new Cat(Color.GOLD);
//        Cat catGold2 = new Cat(Color.GOLD);
//        Cat catSilver1 = new Cat(Color.SILVER);
//        Cat catSilver2 = new Cat(Color.SILVER);
//
//        Rabbit rabbitGold1 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold2 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold3 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold4 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold5 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold6 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold7 = new Rabbit(Color.GOLD);
//        Rabbit rabbitGold8 = new Rabbit(Color.GOLD);
//        Rabbit rabbitSilver1 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver2 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver3 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver4 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver5 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver6 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver7 = new Rabbit(Color.SILVER);
//        Rabbit rabbitSilver8 = new Rabbit(Color.SILVER);
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    /**
     * Increases move count until it reaches 4, then
     * ends move
     *
     * */
    public void increaseMoveCount(){
        moveCount++;
        if(moveCount > 3){
            endMove();
        }
    }

    /**
     * Ends players move and gives it to another player
     *
    * */
    public void endMove(){
        if(moveCount > 0){
            if(currentColorMove == Color.GOLD)
                currentColorMove = Color.SILVER;
            else
                currentColorMove = Color.GOLD;

            moveCount = 0;
            logger.info("Move ended, now " + currentColorMove + " moves");
        } else
            logger.warning("Move cannot be ended, now " + currentColorMove + " moves");

    }

    /**
     * Sets game phase
     *
     * @param phase - phase to set
     * */
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public Color getCurrentColorMove() {
        return currentColorMove;
    }

    public void setCurrentColorMove(Color currentColorMove) {
        this.currentColorMove = currentColorMove;
    }
}
