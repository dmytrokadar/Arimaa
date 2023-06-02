package Graphics;

import Figures.*;
import Logic.Board;
import MainWindow.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.sql.SQLOutput;

import static MainWindow.MainWindow.SIDE_SIZE;
import static MainWindow.MainWindow.TILE_SIZE;

public class GameScene extends Scene {

    Tile tiles[][];
    Pane pane;
    BorderPane infoPane;
    Board board;

    public GameScene(boolean load){
        super(new Pane());

        pane = (Pane) this.getRoot();

        tiles = new Tile[SIDE_SIZE][SIDE_SIZE];

        pane.getChildren().add(createBoard());
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

        if(board.getPhase() == Board.Phase.EDIT)
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
        ((Tile)e.getSource()).removeFigure();
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
                            t.setFigureView((FigureView) dragEvent.getGestureSource());
                            ((FigureView) dragEvent.getGestureSource()).setPosX(t.getPosX());
                            ((FigureView) dragEvent.getGestureSource()).setPosY(t.getPosY());

                            if ((t.getPosY() == 2 && (t.getPosX() == 2 || t.getPosX() == 5)) || (t.getPosY() == 5 &&
                                    (t.getPosX() == 2 || t.getPosX() == 5))) {
                                System.out.println(t.getFigureView().getFigure().getColor() + " " + t.getFigureView().getPosY() + " " + t.getFigureView().getPosX());
                                if (!friendlyFigureNear(t.getFigureView())) {
                                    t.removeFigure();
                                }
                            }


                            Board.Color win = checkForWin();
                            System.out.println(win + "win");
                            if(win != null){
                                board.setPhase(Board.Phase.END);
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

        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                board.setPhase(Board.Phase.GAME);
                start.setDisable(true);
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
            }
        });

        Button forward = new Button("->");
        Button backward = new Button("<-");

        HBox buttons = new HBox(backward, start, forward);
        HBox downButtons = new HBox(exit, endMove);
        buttons.setAlignment(Pos.CENTER);
        downButtons.setAlignment(Pos.BOTTOM_LEFT);

        infoPane.setCenter(buttons);
        infoPane.setBottom(downButtons);
        pane.getChildren().add(infoPane);
    }
}
