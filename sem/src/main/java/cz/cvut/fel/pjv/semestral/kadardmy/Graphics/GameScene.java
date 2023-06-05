package cz.cvut.fel.pjv.semestral.kadardmy.Graphics;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.*;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.BoardState;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Move;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.MoveHolder;
import cz.cvut.fel.pjv.semestral.kadardmy.Utilities.GameRecorder;
import cz.cvut.fel.pjv.semestral.kadardmy.Utilities.Timer;
import cz.cvut.fel.pjv.semestral.kadardmy.Utilities.UpdateTime;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board.logger;
import static cz.cvut.fel.pjv.semestral.kadardmy.MainWindow.MainWindow.SIDE_SIZE;
import static cz.cvut.fel.pjv.semestral.kadardmy.MainWindow.MainWindow.TILE_SIZE;
import static cz.cvut.fel.pjv.semestral.kadardmy.Utilities.Timer.SECONDS;


public class GameScene extends Scene {

    Tile tiles[][];

    private Label time1;
    private Label time2;
    private Label gameState;
    private Label whoMoves;

    private Timer timer1;
    private Timer timer2;

    private Pane pane;
    private BorderPane infoPane;
    private Board board;
    private MoveHolder moveHolder;
    private GameRecorder gameRecorder;
    private GridPane boardGP;

    private Button forward;
    private Button backward;

    private long timer1Time = SECONDS;
    private long timer2Time = SECONDS;

    private List<FigureView> figuresList;
    private final static int[][] DIRECTIONS = {{0,1}, {1,0}, {-1, 0}, {0, -1}};

    private boolean aiGOld;
    private boolean aiSilver;

    boolean rabbitPush = false;

    /**
     * MainScene with the game
     *
     * @param load - load from file or new game
     * */
    public GameScene(boolean load, boolean aiGOld, boolean aiSilver){
        super(new Pane());
        this.aiGOld = aiGOld;
        this.aiSilver = aiSilver;

        pane = (Pane) this.getRoot();

        tiles = new Tile[SIDE_SIZE][SIDE_SIZE];

        figuresList = new ArrayList<>();

        boardGP = createBoard();

        moveHolder = new MoveHolder();
        board = new Board();

        gameRecorder = new GameRecorder();
        populateBoard(load);

        pane.getChildren().add(boardGP);
        createPane();



    }

    public Tile getRandomMove(){
        // TODO вибрати рандомну кількість ходу
        //  вибирати рандомний тайл з фігурков, якшо мош ходити то гуд, якшо нє то інший узяти
        int moveCount = ThreadLocalRandom.current().nextInt(1, 5);
        for (int i = 0; i < moveCount; i++){
            int ind = ThreadLocalRandom.current().nextInt(figuresList.size()) /*% figuresList.size()*/;
            FigureView fw = figuresList.get(ind);


        }

        return tiles[0][0];
    }

    public Board.Color checkForWin(){

        if (timer1.getTime() == 0){
            timer1.pauseTimer();
            timer2.pauseTimer();
            return timer2.getColor();
        }

        if (timer2.getTime() == 0){
            timer1.pauseTimer();
            timer2.pauseTimer();
            return timer1.getColor();
        }

        for(Tile tilesL[] : tiles){
            Tile tile = tilesL[0];
            if(tile.getFigureView() != null && tile.getFigureView().getFigure() instanceof Rabbit &&
                    tile.getFigureView().getFigure().getColor() == Board.Color.GOLD){
                timer1.pauseTimer();
                timer2.pauseTimer();
                return  Board.Color.GOLD;
            }
            tile = tilesL[SIDE_SIZE - 1];
            if(tile.getFigureView() != null && tile.getFigureView().getFigure() instanceof Rabbit &&
                    tile.getFigureView().getFigure().getColor() == Board.Color.SILVER){
                timer1.pauseTimer();
                timer2.pauseTimer();
                return  Board.Color.SILVER;
            }
        }

//        for(Tile tile : tiles[SIDE_SIZE - 1]){
//            if(tile.getFigureView() != null && tile.getFigureView().getFigure() instanceof Rabbit &&
//                    tile.getFigureView().getFigure().getColor() == Board.Color.SILVER){
//                return  Board.Color.SILVER;
//            }
//        }

        int count = 0;
        int frozenCount = 0;
        for(Tile tiles_loc[]:tiles){
            for (Tile tile : tiles_loc){
                count++;
                if(tile.getFigureView() != null){
                    tile.getFigureView().getFigure().setFrozen(checkIsFreesed(tile.getFigureView()));
                    if (tile.getFigureView().getFigure().isFrozen()){
                        frozenCount++;
                    }
                }
            }
//            System.out.println();
        }

        if(count == frozenCount){
            timer1.pauseTimer();
            timer2.pauseTimer();
            return Board.Color.SILVER;
        }

        count = 0;
        frozenCount = 0;
        for(Tile tiles_loc[]:tiles){
            for (Tile tile : tiles_loc){
                count++;
                if (tile.getFigureView() != null && tile.getFigureView().getFigure().getColor() == Board.Color.SILVER){
                    frozenCount++;
                }
            }
//            System.out.println();
        }
        if(count == frozenCount){
            timer1.pauseTimer();
            timer2.pauseTimer();
            return Board.Color.GOLD;
        }

        return null;
    }

    /**
     * Gets the biggest strengths from nearest figures of needed color
     * */
    public Figure.STRENGTH friendlyFigureNear(FigureView fw, Board.Color color){
        int posX = fw.getPosX();
        int posY = fw.getPosY();
        Figure.STRENGTH maxS = null;

        if(posX != 0){
            if(tiles[posX-1][posY].getFigureView() != null && tiles[posX-1][posY].getFigureView().getFigure().getColor()
                    == color)
                maxS = tiles[posX-1][posY].getFigureView().getFigure().getStrength();
        }
        if(posX != SIDE_SIZE-1){
            if(tiles[posX+1][posY].getFigureView() != null && tiles[posX+1][posY].getFigureView().getFigure().getColor()
                    == color)
                if((maxS == null) ||
                        (maxS.getValue() < tiles[posX+1][posY].getFigureView().getFigure().getStrength().getValue()))
                    maxS = tiles[posX+1][posY].getFigureView().getFigure().getStrength();
        }

        if(posY != 0){
            if(tiles[posX][posY-1].getFigureView() != null && tiles[posX][posY-1].getFigureView().getFigure().getColor()
                    == color){
                if((maxS == null) ||
                        (maxS.getValue() < tiles[posX][posY-1].getFigureView().getFigure().getStrength().getValue()))
                maxS = tiles[posX][posY-1].getFigureView().getFigure().getStrength();
            }
        }
        if(posY != SIDE_SIZE-1){
            if(tiles[posX][posY+1].getFigureView() != null && tiles[posX][posY+1].getFigureView().getFigure().getColor()
                    == color){
                if((maxS == null) || (maxS.getValue() <
                        tiles[posX][posY+1].getFigureView().getFigure().getStrength().getValue()))
                    maxS = tiles[posX][posY+1].getFigureView().getFigure().getStrength();
            }
        }

        return maxS;
    }

    /**
     * Returns true if figure can be pulled
     * */
    public boolean friendlyFigureNearPull(FigureView fw,
                                          int posXTile, int posYTile,
                                          int posXTileDesired, int posYTileDesired, Figure.STRENGTH tileStrength){
        if(posXTile == posXTileDesired
                && posYTile == posYTileDesired
                && tileStrength.getValue() > fw.getFigure().getStrength().getValue())
            return true;

        return false;
    }

    public boolean checkIsFreesed(FigureView fw){
        Board.Color color = fw.getFigure().getColor();
        if(color == Board.Color.GOLD){
            color = Board.Color.SILVER;
        } else {
            color = Board.Color.GOLD;
        }

        if(!friendlyFigureNear(fw) && (friendlyFigureNear(fw, color) != null) &&
                friendlyFigureNear(fw, color).getValue() > fw.getFigure().getStrength().getValue()){
            return true;
        }

        return false;
    }

    public boolean friendlyFigureNear(FigureView fw){
        int posX = fw.getPosX();
        int posY = fw.getPosY();

//        for(Tile tiles_loc[]:tiles){
//            for (Tile tile : tiles_loc){
//                System.out.print((tile.getFigureView() != null) + " ");
//            }
//            System.out.println();
//        }

        if(posX != 0){
            if(tiles[posX-1][posY].getFigureView() != null && tiles[posX-1][posY].getFigureView().getFigure().getColor()
                == fw.getFigure().getColor())
                return true;
        }
        if(posX != SIDE_SIZE-1){
            if(tiles[posX+1][posY].getFigureView() != null && tiles[posX+1][posY].getFigureView().getFigure().getColor()
                    == fw.getFigure().getColor())
                return true;
        }

        if(posY != 0){
            if(tiles[posX][posY-1].getFigureView() != null && tiles[posX][posY-1].getFigureView().getFigure().getColor()
                    == fw.getFigure().getColor()){
                return true;
            }
        }
        if(posY != SIDE_SIZE-1){
            if(tiles[posX][posY+1].getFigureView() != null && tiles[posX][posY+1].getFigureView().getFigure().getColor()
                    == fw.getFigure().getColor())
                return true;
        }

        return false;
    }

    private boolean validMove(FigureView fw, Tile tile){
        int posX = fw.getPosX();
        int posY = fw.getPosY();

        getRandomMove();
//
//        System.out.println(fw.getFigure().getColor());
        fw.getFigure().setFrozen(checkIsFreesed(fw));

        if(board.getPhase() == Board.Phase.END){
            timer1.pauseTimer();
            timer2.pauseTimer();
            return false;
        }

        if(board.getPhase() == Board.Phase.EDIT){
            return true;
        }

        if(tile.getFigureView() != null){
            return false;
        }

        if(fw.getFigure().getColor() != board.getCurrentColorMove()){
            rabbitPush = true;

            Figure.STRENGTH tmp = friendlyFigureNear(fw, board.getCurrentColorMove());
            if(tmp == null){
                Move move = moveHolder.getCurrentMove();
                if(move == null)
                    return false;

                if(!friendlyFigureNearPull(fw,
                        move.getPosXFrom(), move.getPosYFrom(),
                        tile.getPosX(), tile.getPosY(), move.getFigure().getStrength()))
                    return false;
            }
            else {
                if (fw.getFigure().getStrength().getValue() >= tmp.getValue()){
                    return false;
                }
            }
        }

        if(fw.getFigure().isFrozen() && !rabbitPush){
            return false;
        }



        if(tile.getPosX() == posX + 1 && tile.getPosY() == posY){
            return true;
        }
        if(tile.getPosX() == posX - 1 && tile.getPosY() == posY){
            return true;
        }
        if(tile.getPosY() == posY - 1 && tile.getPosX() == posX &&
                (!(fw.getFigure() instanceof Rabbit && fw.getFigure().getColor() == Board.Color.SILVER) || rabbitPush)){
            rabbitPush = false;
            return true;
        }
        if(tile.getPosY() == posY + 1 && tile.getPosX() == posX &&
                (!(fw.getFigure() instanceof Rabbit && fw.getFigure().getColor() == Board.Color.GOLD) || rabbitPush)){
            rabbitPush = false;
            return true;
        }

        return false;
    }

    private EventHandler<DragEvent> onDragOver = e -> {
        if(e.getSource() != e.getGestureSource()){
            e.acceptTransferModes(TransferMode.MOVE);
//            System.out.println("dragged to tile");
        }

        e.consume();
    };

    private EventHandler<DragEvent> onDragDone = e -> {
//        ((Tile)e.getSource()).removeFigure();
    };

    private EventHandler<MouseEvent> onDragDetectedFigure = e ->{
        // inspiration https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
            Dragboard db = ((FigureView)e.getSource()).startDragAndDrop(TransferMode.MOVE);
            logger.info("enter drag");

            ClipboardContent content = new ClipboardContent();
            content.putImage(((FigureView)e.getSource()).getImage());
            db.setContent(content);

            e.consume();
    };

    private EventHandler<DragEvent> setOnDragDroppedFigure = e -> {
            Dragboard db = e.getDragboard();
            logger.info("exit drag");



            e.setDropCompleted(true);

            e.consume();
        };

    private Figure getRandomFigure(){
        return new Camel(Board.Color.GOLD);
    }

    private void populateBoard(boolean load){
        // init figures
        // first phase will be as in original game client - figures are already on board,
        // but you can change their position
        BoardState boardState = gameRecorder.readFromFileFile();
        FigureView fw = null;
        if(!load || boardState == null){
            for (int i = 0; i < SIDE_SIZE; i++){
                for (int j = 0; j < SIDE_SIZE; j++){
                    if(i == SIDE_SIZE - 2){
                        fw = new FigureView(new Image("Textures/rabbit_g.png"), j, i, new Rabbit(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 1){
                        fw = new FigureView(new Image("Textures/rabbit_s.png"), j, i, new Rabbit(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j < 2)){
                        fw = new FigureView(new Image("Textures/cat_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j < 2)){
                        fw = new FigureView(new Image("Textures/cat_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j >= 2 && j < 4)){
                        fw = new FigureView(new Image("Textures/dog_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j >= 2 && j < 4)){
                        fw = new FigureView(new Image("Textures/dog_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j >= 4 && j < 6)){
                        fw = new FigureView(new Image("Textures/horse_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j >= 4 && j < 6)){
                        fw = new FigureView(new Image("Textures/horse_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j == 6)){
                        fw = new FigureView(new Image("Textures/camel_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j == 6)){
                        fw = new FigureView(new Image("Textures/camel_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j == SIDE_SIZE - 1)){
                        fw = new FigureView(new Image("Textures/elephant_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j == SIDE_SIZE - 1)){
                        fw = new FigureView(new Image("Textures/elephant_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(fw != null){
                        figuresList.add(fw);
                        fw = null;
                    }
                }
            }
        } else {
            char figureL;
            int posX, posY;
            Figure f;

            timer1Time = boardState.getTime1();
            timer2Time = boardState.getTime2();

            board.setCurrentColorMove(boardState.getColor());

            String[] moves = boardState.getMoves();

            for(String move : moves){
                figureL = move.charAt(0);
                posX = GameRecorder.posToInt(Character.getNumericValue(move.charAt(2)));
                posY = GameRecorder.stringToInt(move.charAt(1));

                f = GameRecorder.stringToFigure(figureL, posX, posY);

                fw = new FigureView(new Image(f.getImagePath()), posX, posY, f);
                fw.setOnDragDetected(onDragDetectedFigure);
                fw.setOnDragDropped(setOnDragDroppedFigure);
                tiles[posX][posY].setFigureView(fw);
                figuresList.add(fw);
            }
        }
    }

    GridPane createBoard(){
        GridPane gp = new GridPane();
        final Image im = new Image("Textures/camel_g.png");
        final FigureView imageView = new FigureView(im, 0, 0, new Camel(Board.Color.GOLD, 0, 0));

        for (int i = 0; i < SIDE_SIZE; i++){
            for (int j = 0; j < SIDE_SIZE; j++){
                Rectangle r = new Rectangle(TILE_SIZE, TILE_SIZE, Color.WHITE);
                if((i == 2 && (j == 2 || j == 5)) || (i == 5 && (j == 2 || j == 5))){
                    r.setFill(Color.GREEN);
                }
                r.setStroke(Color.BLACK);
//                r.setOnDragOver(onDragOver);
                Tile t = new Tile(r, j, i);
                t.setOnDragOver(onDragOver);
                t.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        Dragboard db = dragEvent.getDragboard();
                        logger.info("exit drag");
//                        t.setFigureView((new ImageView(db.getImage())));

                        if(validMove((FigureView) dragEvent.getGestureSource(), t)) {
                            dragEvent.setDropCompleted(true);
                            FigureView fw = ((FigureView) dragEvent.getGestureSource());


                            Tile tileSource = (Tile)fw.getParent();
                            FigureView fwTarget = t.getFigureView();
                            if(fwTarget != null){
                                tileSource.removeFigure();
                                tileSource.setFigureView(fwTarget);
                                fwTarget.setPosX(tileSource.getPosX());
                                fwTarget.setPosY(tileSource.getPosY());
                                logger.info("swapped "+ t.getPosX()  + " " + t.getPosY() + " " +
                                        tileSource.getPosX() + " " + tileSource.getPosY());
                            } else {
                                tileSource.removeFigure();
                            }
                            Move move = new Move(fw.getFigure(), fw.getPosX(), fw.getPosY(), t.getPosX(), t.getPosY(), board.getCurrentColorMove(), board.getMoveCount());

                            t.removeFigure();
                            t.setFigureView(fw);
                            fw.setPosX(t.getPosX());
                            fw.setPosY(t.getPosY());


                            if(board.getPhase() != Board.Phase.EDIT){
                                if ((t.getPosY() == 2 && (t.getPosX() == 2 || t.getPosX() == 5)) || (t.getPosY() == 5 &&
                                        (t.getPosX() == 2 || t.getPosX() == 5))) {
                                    logger.info(t.getFigureView().getFigure().getColor() + " " + t.getFigureView().getPosY() + " " + t.getFigureView().getPosX());
                                    if (!friendlyFigureNear(t.getFigureView())) {
                                        t.removeFigure();
                                    }
                                }

                                if(moveHolder.canGoForward()){
                                    moveHolder.removeLastMoves();
                                    if(board.getCurrentColorMove() == timer1.getColor()){
                                        timer2.pauseTimer();
                                        timer1.startTimer();
                                    } else {
                                        timer1.pauseTimer();
                                        timer2.startTimer();
                                    }
                                }
                                moveHolder.appendMove(move);
                                backward.setDisable(false);

                                Board.Color win = checkForWin();
                                logger.info(win + "win");
                                if(win != null){
                                    board.setPhase(Board.Phase.END);
                                    gameState.setText(win + " WIN!!!!!!!");
                                }
                                board.increaseMoveCount();
                                whoMoves.setText(board.getCurrentColorMove() + " Moves");
                                if(board.getCurrentColorMove() == timer1.getColor()){
                                    timer2.pauseTimer();
                                    timer1.startTimer();
                                } else {
                                    timer1.pauseTimer();
                                    timer2.startTimer();
                                }
                            }

                        } else {
                            dragEvent.setDropCompleted(false);
                        }

                        dragEvent.consume();
                    }
                });
                t.setOnDragDone(onDragDone);
                tiles[j][i] = t;
                //TODO mb j i i pominyaty mistamy
                gp.add(t, j, i);
            }
        }

//        imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
//            // inspiration https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Dragboard db = imageView.startDragAndDrop(TransferMode.MOVE);
//                System.out.println("enter drag");
//
//                ClipboardContent content = new ClipboardContent();
//                content.putImage(imageView.getImage());
//                db.setContent(content);
//
//                mouseEvent.consume();
//            }
//        });
//
//        imageView.setOnDragDropped(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent dragEvent) {
//                Dragboard db = dragEvent.getDragboard();
//                System.out.println("exit drag");
//
//
//
//                dragEvent.setDropCompleted(true);
//
//                dragEvent.consume();
//            }
//        });
//
//        imageView.setOnDragDone(new EventHandler<DragEvent>() {
//            @Override
//            public void handle(DragEvent dragEvent) {
////                imageView.setImage(null);
//            }
//        });
//
//        gp.getChildren().add(imageView);
//        gp.getChildren().add(new ImageView(im));

        return gp;
    }

    private void createPane(){
        infoPane = new BorderPane();
        infoPane.layoutXProperty().bind(boardGP.widthProperty());
        infoPane.prefWidthProperty().bind(widthProperty().subtract(boardGP.widthProperty()));
        infoPane.prefHeightProperty().bind(heightProperty());
        infoPane.setPadding(new Insets(20));

        gameState = new Label("Editing");
        gameState.setFont(Font.font("Impact", 50));
        gameState.setAlignment(Pos.CENTER);
        time1 = new Label(String.format("%02d:%02d", (timer1Time / 60) % 60, timer1Time % 60));
        time1.setFont(Font.font("Impact", 30));
        time1.setAlignment(Pos.CENTER_LEFT);
        time2 = new Label(String.format("%02d:%02d", (timer2Time / 60) % 60, timer2Time % 60));
        time2.setFont(Font.font("Impact", 30));
        time2.setAlignment(Pos.CENTER_LEFT);
        whoMoves = new Label( board.getCurrentColorMove() + " Moves");
        whoMoves.setFont(Font.font("Impact", 30));
        whoMoves.setAlignment(Pos.CENTER_LEFT);

        timer1 = new Timer(Board.Color.GOLD, timer2Time);
        timer1.getProperty().addListener(new UpdateTime(time2));

        timer2 = new Timer(Board.Color.SILVER, timer1Time);
        timer2.getProperty().addListener(new UpdateTime(time1));


        VBox times = new VBox(40, time1, time2, gameState, whoMoves);
        times.setAlignment(Pos.CENTER);
        infoPane.setTop(times);

        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                board.setPhase(Board.Phase.GAME);
                gameState.setText("Game");
                start.setDisable(true);
                if(board.getCurrentColorMove() == timer1.getColor()){
                    timer1.startTimer();
                } else {
                    timer2.startTimer();
                }
            }
        });

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        Button endMove = new Button("End Move");
        endMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                board.endMove();
                whoMoves.setText(board.getCurrentColorMove() + " Moves");
                if(board.getCurrentColorMove() == timer1.getColor()){
                    timer2.pauseTimer();
                    timer1.startTimer();
                } else {
                    timer1.pauseTimer();
                    timer2.startTimer();
                }
            }
        });

        forward = new Button("->");
        forward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(moveHolder.canGoForward()){
                    moveHolder.goForward();
                    Move move = moveHolder.getCurrentMove();
                    FigureView fw = tiles[move.getPosXFrom()][move.getPosYFrom()].getFigureView();
                    tiles[move.getPosXFrom()][move.getPosYFrom()].removeFigure();

                    fw.setPosX(move.getPosXTo());
                    fw.setPosX(move.getPosYTo());

                    tiles[move.getPosXTo()][move.getPosYTo()].setFigureView(fw);
                    board.setCurrentColorMove(move.getColor());
                    board.setMoveCount(move.getMoveCount());
                    whoMoves.setText(board.getCurrentColorMove() + " Moves");
                    backward.setDisable(false);
                    if(!moveHolder.canGoForward()){
                        forward.setDisable(true);
                    }

                    logger.info("Going forward");
                } else {
                    forward.setDisable(true);
                }

            }
        });
        forward.setDisable(true);
        backward = new Button("<-");
        backward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(moveHolder.canGoBack()){
                    Move move = moveHolder.getCurrentMove();
                    FigureView fw = tiles[move.getPosXTo()][move.getPosYTo()].getFigureView();
                    tiles[move.getPosXTo()][move.getPosYTo()].removeFigure();

//                    System.out.println(fw.getPosX() + " " + fw.getPosY());
                    fw.setPosX(move.getPosXFrom());
                    fw.setPosX(move.getPosYFrom());
//                    System.out.println(fw.getPosX() + " " + fw.getPosY());

                    tiles[move.getPosXFrom()][move.getPosYFrom()].setFigureView(fw);
                    board.setCurrentColorMove(move.getColor());
                    board.setMoveCount(move.getMoveCount());
                    whoMoves.setText(board.getCurrentColorMove() + " Moves");
                    forward.setDisable(false);
                    moveHolder.goBack();
                    if(!moveHolder.canGoBack()){
                        backward.setDisable(true);
                    }
                    logger.info("Going Backward");
                } else {
                    backward.setDisable(true);
                }
            }
        });
        backward.setDisable(true);
        Button saveGame = new Button("Save Game");
        saveGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameRecorder.getCurrentGameState(tiles, timer1.getTime(), timer2.getTime(), board.getCurrentColorMove());
            }
        });

        HBox buttons = new HBox(10, backward, start, forward);
        HBox downButtons = new HBox(20, exit, saveGame, endMove);
        buttons.setAlignment(Pos.CENTER);
        downButtons.setAlignment(Pos.CENTER);

        infoPane.setCenter(buttons);
        infoPane.setBottom(downButtons);
        pane.getChildren().add(infoPane);
    }
}
