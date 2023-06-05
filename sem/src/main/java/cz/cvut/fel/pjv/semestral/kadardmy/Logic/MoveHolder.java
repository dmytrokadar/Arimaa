package cz.cvut.fel.pjv.semestral.kadardmy.Logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MoveHolder {
    public final static int FIRST_MOVE = 0;

    List<Move> moveArray = new ArrayList<>();
    private int currentMoveIndex = -1;

    public MoveHolder() {
    }

    public void appendMove(Move move){
        moveArray.add(move);
        currentMoveIndex++;
    }

    public int getMoveSize(){
        return moveArray.size();
    }

    public boolean canGoBack(){
        if(currentMoveIndex < 0){
            return false;
        }

        return true;
    }

    public boolean canGoForward(){
        if(currentMoveIndex >= getMoveSize() - 1){
            return false;
        }

        return true;
    }

    /**
     * Sets pointer on previous move and returns false. If the move is first, returns false.
     *
     * */
    public boolean goBack(){
        if(currentMoveIndex < 0)
            return false;

        currentMoveIndex--;
        return true;
    }

    public boolean goForward(){
        if(currentMoveIndex == getMoveSize() - 1)
            return false;

        currentMoveIndex++;
        return true;
    }

    public void removeLastMoves(){
        moveArray = moveArray.subList(FIRST_MOVE, currentMoveIndex+1);
    }

    public Move getCurrentMove(){
        if(currentMoveIndex < 0){
            return null;
        }
        return moveArray.get(currentMoveIndex);
    }

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
