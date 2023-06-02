package Graphics;

import Figures.*;
import Logic.Board;
import MainWindow.MainWindow;
import Utilities.Timer;
import Utilities.UpdateTime;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.SQLOutput;
import java.sql.Time;

import static Logic.Board.logger;
import static MainWindow.MainWindow.SIDE_SIZE;
import static MainWindow.MainWindow.TILE_SIZE;

public class GameScene extends Scene {

    Tile tiles[][];

    Label time1;
    Label time2;
    Label gameState;
    Label whoMoves;

    Timer timer1;
    Timer timer2;

    Pane pane;
    BorderPane infoPane;
    Board board;
    GridPane boardGP;

    public GameScene(boolean load){
        super(new Pane());

        pane = (Pane) this.getRoot();

        tiles = new Tile[SIDE_SIZE][SIDE_SIZE];

        boardGP = createBoard();
        pane.getChildren().add(boardGP);
        createPane();

        populateBoard(load);

        board = new Board();
    }

    private Board.Color checkForWin(){

        for(Tile tilesL[] : tiles){
            Tile tile = tilesL[0];
            if(tile.getFigureView() != null && tile.getFigureView().getFigure() instanceof Rabbit &&
                    tile.getFigureView().getFigure().getColor() == Board.Color.GOLD){
                return  Board.Color.GOLD;
            }
            tile = tilesL[SIDE_SIZE - 1];
            if(tile.getFigureView() != null && tile.getFigureView().getFigure() instanceof Rabbit &&
                    tile.getFigureView().getFigure().getColor() == Board.Color.SILVER){
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
                if (tile.getFigureView() != null && tile.getFigureView().getFigure().getColor() == Board.Color.GOLD){
                    frozenCount++;
                }
            }
//            System.out.println();
        }

        if(count == frozenCount){
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
            return Board.Color.GOLD;
        }

        return null;
    }

    public boolean friendlyFigureNear(FigureView fw){
        int posX = fw.getPosX();
        int posY = fw.getPosY();

        System.out.println(fw.getFigure().getColor() + "fFigureNear");
        for(Tile tiles_loc[]:tiles){
            for (Tile tile : tiles_loc){
                System.out.print((tile.getFigureView() != null) + " ");
            }
            System.out.println();
        }

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
                System.out.println(fw.getFigure().getColor() + " " + tiles[posX][posY-1].getFigureView().getFigure().getColor());
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
        System.out.println(fw.getFigure().getColor());


        if(board.getPhase() == Board.Phase.EDIT){
            return true;
        }

        if(tile.getFigureView() != null){
            return false;
        }

        if(fw.getFigure().getColor() != board.getCurrentColorMove()){
            return false;
        }

        if(tile.getPosX() == posX + 1 && tile.getPosY() == posY){
            return true;
        }
        if(tile.getPosX() == posX - 1 && tile.getPosY() == posY){
            return true;
        }
        if(tile.getPosY() == posY - 1 && tile.getPosX() == posX &&
                !(fw.getFigure() instanceof Rabbit && fw.getFigure().getColor() == Board.Color.SILVER)){
            return true;
        }
        if(tile.getPosY() == posY + 1 && tile.getPosX() == posX &&
                !(fw.getFigure() instanceof Rabbit && fw.getFigure().getColor() == Board.Color.GOLD)){
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
            System.out.println("enter drag");

            ClipboardContent content = new ClipboardContent();
            content.putImage(((FigureView)e.getSource()).getImage());
            db.setContent(content);

            e.consume();
    };

    private EventHandler<DragEvent> setOnDragDroppedFigure = e -> {
            Dragboard db = e.getDragboard();
            System.out.println("exit drag");



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
        if(!load){
            for (int i = 0; i < SIDE_SIZE; i++){
                for (int j = 0; j < SIDE_SIZE; j++){
                    if(i == SIDE_SIZE - 2){
                        FigureView fw = new FigureView(new Image("Textures/rabbit_g.png"), j, i, new Rabbit(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 1){
                        FigureView fw = new FigureView(new Image("Textures/rabbit_s.png"), j, i, new Rabbit(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j < 2)){
                        FigureView fw = new FigureView(new Image("Textures/cat_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j < 2)){
                        FigureView fw = new FigureView(new Image("Textures/cat_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j >= 2 && j < 4)){
                        FigureView fw = new FigureView(new Image("Textures/dog_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j >= 2 && j < 4)){
                        FigureView fw = new FigureView(new Image("Textures/dog_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j >= 4 && j < 6)){
                        FigureView fw = new FigureView(new Image("Textures/horse_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j >= 4 && j < 6)){
                        FigureView fw = new FigureView(new Image("Textures/horse_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j == 6)){
                        FigureView fw = new FigureView(new Image("Textures/camel_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j == 6)){
                        FigureView fw = new FigureView(new Image("Textures/camel_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == SIDE_SIZE - 1 && (j == SIDE_SIZE - 1)){
                        FigureView fw = new FigureView(new Image("Textures/elephant_g.png"), j, i, new Cat(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                    if(i == 0 && (j == SIDE_SIZE - 1)){
                        FigureView fw = new FigureView(new Image("Textures/elephant_s.png"), j, i, new Cat(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw);
                    }
                }
            }
        } else {

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
                        System.out.println("exit drag");
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
                            t.removeFigure();
                            t.setFigureView(fw);
                            fw.setPosX(t.getPosX());
                            fw.setPosY(t.getPosY());


                            if(board.getPhase() != Board.Phase.EDIT){
                                if ((t.getPosY() == 2 && (t.getPosX() == 2 || t.getPosX() == 5)) || (t.getPosY() == 5 &&
                                        (t.getPosX() == 2 || t.getPosX() == 5))) {
                                    System.out.println(t.getFigureView().getFigure().getColor() + " " + t.getFigureView().getPosY() + " " + t.getFigureView().getPosX());
                                    if (!friendlyFigureNear(t.getFigureView())) {
                                        t.removeFigure();
                                    }
                                }


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
        time1 = new Label("10:00");
        time1.setFont(Font.font("Impact", 30));
        time1.setAlignment(Pos.CENTER_LEFT);
        time2 = new Label("10:00");
        time2.setFont(Font.font("Impact", 30));
        time2.setAlignment(Pos.CENTER_LEFT);
        whoMoves = new Label("GOLD Moves");
        whoMoves.setFont(Font.font("Impact", 30));
        whoMoves.setAlignment(Pos.CENTER_LEFT);

        timer1 = new Timer(Board.Color.GOLD);
        timer1.getProperty().addListener(new UpdateTime(time2));

        timer2 = new Timer(Board.Color.SILVER);
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
                timer1.startTimer();
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

        Button forward = new Button("->");
        Button backward = new Button("<-");

        HBox buttons = new HBox(10, backward, start, forward);
        HBox downButtons = new HBox(20, exit, endMove);
        buttons.setAlignment(Pos.CENTER);
        downButtons.setAlignment(Pos.CENTER);

        infoPane.setCenter(buttons);
        infoPane.setBottom(downButtons);
        pane.getChildren().add(infoPane);
    }
}
