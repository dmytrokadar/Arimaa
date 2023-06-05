package cz.cvut.fel.pjv.semestral.kadardmy.Utilities;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.*;
import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.Tile;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.BoardState;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board.logger;
import static cz.cvut.fel.pjv.semestral.kadardmy.MainWindow.MainWindow.SIDE_SIZE;

public class GameRecorder {
    /*
     * Records game(figures placement, figures moves)
     * */
    private final static Character letters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'} ;
    private final static Integer numbers[] = {8, 7, 6, 5, 4, 3, 2, 1};
    private static List<Character> lettersList = Arrays.asList(letters);
    private static List<Integer> numbersList = Arrays.asList(numbers);

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

    public static Figure stringToFigure(Character c, int posX, int posY){
        Board.Color color = Board.Color.GOLD;

        if(Character.isLowerCase(c)){
            color = Board.Color.SILVER;
            c = Character.toUpperCase(c);
        }

        switch (c){
            case 'C':
                return new Cat(color, posX, posY);
            case 'M':
                return new Camel(color, posX, posY);
            case 'D':
                return new Dog(color, posX, posY);
            case 'E':
                return new Elephant(color, posX, posY);
            case 'H':
                return new Horse(color, posX, posY);
            case 'R':
                return new Rabbit(color, posX, posY);
        }

        return null;
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

    public static Board.Color stringToColor(String str){
        if(str.equals("SILVER")){
            return Board.Color.SILVER;
        }

        return Board.Color.GOLD;
    }

    public static BoardState readFromFileFile(){
        BoardState boardState = null;
        try {
            File newFile = new File("save.txt");
            Scanner scanner = new Scanner(newFile);

            long time1 = 0;
            long time2 = 0;
            Board.Color color = Board.Color.GOLD;
            String[] moves = null;

//            BoardState boardState = null;

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.equals("Time 1:")){
                    line = scanner.nextLine();
                    time1 = Long.parseLong(line);
                }

                if(line.equals("Time 2:")){
                    line = scanner.nextLine();
                    time2 = Long.parseLong(line);
                }

                if(line.equals("Current Move:")){
                    line = scanner.nextLine();
                    color = stringToColor(line);
                }
                if(line.equals("Position:")){
                    line = scanner.nextLine();
                    moves = line.split(" ");

                    logger.info("Loaded from file");
                }
            }

            boardState = new BoardState(moves, time1, time2, color);

            if(moves == null)
                logger.warning("Nothing to load");
            return boardState;
        } catch (IOException e) {
            logger.warning("Cannot read from file " + e);
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrentGameState(Tile tiles[][], long time1, long time2, Board.Color color){
        StringBuilder ret = new StringBuilder();

        ret.append("Time 1:\n").append(time1).append("\n").append("Time 2:\n").append(time2).append("\n");
        ret.append("Current Move:\n").append(color).append("\n");
        ret.append("Position:\n");
        for (int i = 0; i < SIDE_SIZE; i++){
            for (int j = 0; j < SIDE_SIZE; j++){
                if(tiles[i][j].getFigureView() != null){
                    ret.append(tiles[i][j].getFigureView().getFigure().toString());
                    ret.append(intToString(j));
                    ret.append(intToPos(i));
                    ret.append(" ");
                }
            }
        }

        writeStringToFile(ret.toString());

        return ret.toString();
    }
}
