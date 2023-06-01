package Graphics;

import Figures.*;
import Logic.Board;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static MainWindow.MainWindow.SIDE_SIZE;
import static MainWindow.MainWindow.TILE_SIZE;

public class GameScene extends Scene {

    Tile tiles[][];
    Pane pane;

    public GameScene(boolean load){
        super(new Pane());

        pane = (Pane) this.getRoot();

        tiles = new Tile[SIDE_SIZE][SIDE_SIZE];

        pane.getChildren().add(createBoard());

        populateBoard(load);

        Board b = new Board();
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
        if(!load){
            for (int i = 0; i < SIDE_SIZE; i++){
                for (int j = 0; j < SIDE_SIZE; j++){
                    if(i == SIDE_SIZE - 2){
                        FigureView fw = new FigureView(new Image("Textures/rabbit_g.png"), j, i, new Rabbit(Board.Color.GOLD));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw); new Rabbit(Board.Color.GOLD);
                    }
                    if(i == 1){
                        FigureView fw = new FigureView(new Image("Textures/rabbit_s.png"), j, i, new Rabbit(Board.Color.SILVER));
                        fw.setOnDragDetected(onDragDetectedFigure);
                        fw.setOnDragDropped(setOnDragDroppedFigure);
                        tiles[j][i].setFigureView(fw); new Rabbit(Board.Color.GOLD);
                    }
                    if(i == TILE_SIZE - 1){

                    }
                }
            }

            Camel camelGold = new Camel(Board.Color.GOLD);
            Camel camelSilver = new Camel(Board.Color.SILVER);

            Horse horseGold1 = new Horse(Board.Color.GOLD);
            Horse horseGold2 = new Horse(Board.Color.GOLD);
            Horse horseSilver1 = new Horse(Board.Color.SILVER);
            Horse horseSilver2 = new Horse(Board.Color.SILVER);

            Dog dogGold1 = new Dog(Board.Color.GOLD);
            Dog dogGold2 = new Dog(Board.Color.GOLD);
            Dog dogSilver1 = new Dog(Board.Color.SILVER);
            Dog dogSilver2 = new Dog(Board.Color.SILVER);

            Cat catGold1 = new Cat(Board.Color.GOLD);
            Cat catGold2 = new Cat(Board.Color.GOLD);
            Cat catSilver1 = new Cat(Board.Color.SILVER);
            Cat catSilver2 = new Cat(Board.Color.SILVER);

            Rabbit rabbitGold1 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold2 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold3 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold4 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold5 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold6 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold7 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitGold8 = new Rabbit(Board.Color.GOLD);
            Rabbit rabbitSilver1 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver2 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver3 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver4 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver5 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver6 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver7 = new Rabbit(Board.Color.SILVER);
            Rabbit rabbitSilver8 = new Rabbit(Board.Color.SILVER);
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
                        t.setFigureView((FigureView) dragEvent.getGestureSource());
//                        t.setFigureView((new ImageView(db.getImage())));

                        dragEvent.setDropCompleted(true);

                        dragEvent.consume();
                    }
                });
                t.setOnDragDone(onDragDone);
                tiles[j][i] = t;
                //TODO mb j i i pominyaty mistamy
                gp.add(t, j, i);
            }
        }

        imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
            // inspiration https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard db = imageView.startDragAndDrop(TransferMode.MOVE);
                System.out.println("enter drag");

                ClipboardContent content = new ClipboardContent();
                content.putImage(imageView.getImage());
                db.setContent(content);

                mouseEvent.consume();
            }
        });

        imageView.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                System.out.println("exit drag");



                dragEvent.setDropCompleted(true);

                dragEvent.consume();
            }
        });

        imageView.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
//                imageView.setImage(null);
            }
        });

        gp.getChildren().add(imageView);
//        gp.getChildren().add(new ImageView(im));



        return gp;
    }
}
