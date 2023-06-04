package cz.cvut.fel.pjv.semestral.kadardmy.Utilities;

import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.Tile;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board.logger;
import static cz.cvut.fel.pjv.semestral.kadardmy.MainWindow.MainWindow.SIDE_SIZE;

public class GameRecorder {
    /*
     * Records game(figures placement, figures moves)
     * */
    private final static Character letters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'} ;
    private final static Integer numbers[] = {8, 7, 6, 5, 4, 3, 2, 1};
    private static List<Character> lettersList = null;
    private static List<Integer> numbersList = null;

    public static char intToString(int n){
        return letters[n];
    }

    public static int intToPos(int n){
        return numbers[n];
    }

    public static int stringToInt(Character c){
        return lettersList.indexOf(c);
    }

    public static int posToInt(int n){
        return numbersList.indexOf(n);
    }

    public GameRecorder(){
        lettersList = Arrays.asList(letters);
        numbersList = Arrays.asList(numbers);
    }

    public static void recordRemoval(){

    }

    public static void recordMove(){

    }

    public static String recordGame(){
        return "";
    }

    public static void loadGame(){

    }

    public static void writeStringToFile(String str){
        try {
            //inspiration https://www.w3schools.com/java/java_files_create.asp

            File newFile = new File("save.txt");
            if(newFile.createNewFile()){
                logger.info("Created file for save");
            } else {
                logger.info("Save file already exists");
            }

            FileWriter writer = new FileWriter(newFile);
            writer.write(str);
            writer.close();
            logger.info("Saved");
        } catch (IOException e) {
            logger.warning("Cannot create file " + e);
            e.printStackTrace();
        }

    }

    public static String getCurrentGameState(Tile tiles[][], long time1, long time2, Board.Color color){
        StringBuilder ret = new StringBuilder();

        ret.append("Time 1:\n").append(time1).append("\n").append("Time 2:\n").append(time2).append("\n");
        ret.append("Current Move:\n").append(color).append("\n");
        for (int i = 0; i < SIDE_SIZE; i++){
            for (int j = 0; j < SIDE_SIZE; j++){
                if(tiles[i][j].getFigureView() != null){
                    ret.append(tiles[i][j].getFigureView().getFigure().toString());
                    ret.append(intToPos(i));
                    ret.append(intToString(j));
                    ret.append(" ");
                }
            }
        }

        writeStringToFile(ret.toString());

        return ret.toString();
    }
}
