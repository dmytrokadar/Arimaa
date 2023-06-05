package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MoveHolder {
    public final static int FIRST_MOVE = 0;

    List<Move> moveArray = new ArrayList<>();
    private int currentMoveIndex = -1;

    /**
     * Holds all moves in list
     *
     * */
    public MoveHolder() {
    }

    /**
     * Appends list with moves
     *
     * @param move - move to append
     * */
    public void appendMove(Move move){
        moveArray.add(move);
        currentMoveIndex++;
    }

    public int getMoveSize(){
        return moveArray.size();
    }

    /**
     * Checks if can go back in history
     *
     * */
    public boolean canGoBack(){
        if(currentMoveIndex < 0){
            return false;
        }

        return true;
    }

    /**
     * Checks if can go forward in history
     *
     * */
    public boolean canGoForward(){
        if(currentMoveIndex >= getMoveSize() - 1){
            return false;
        }

        return true;
    }

    /**
     * Sets pointer on previous move and returns true. If the move is first, returns false.
     *
     * */
    public boolean goBack(){
        if(currentMoveIndex < 0)
            return false;

        currentMoveIndex--;
        return true;
    }

    /**
     * Sets pointer on next move and returns true. If the move is last, returns false.
     *
     * */
    public boolean goForward(){
        if(currentMoveIndex == getMoveSize() - 1)
            return false;

        currentMoveIndex++;
        return true;
    }

    /**
     * Removes last moves after pointer
     * */
    public void removeLastMoves(){
        moveArray = moveArray.subList(FIRST_MOVE, currentMoveIndex+1);
    }

    /**
     * Returns last move
     * */
    public Move getCurrentMove(){
        if(currentMoveIndex < 0){
            return null;
        }
        return moveArray.get(currentMoveIndex);
    }

    /**
     * Gets move before last
     * */
    public Move getPreviousMove(){
        if(currentMoveIndex > 0){
            return moveArray.get(currentMoveIndex - 1);
        }
        return null;
    }

    public int getCurrentMoveIndex() {
        return currentMoveIndex;
    }
}
