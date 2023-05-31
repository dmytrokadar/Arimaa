package MainWindow;
import Graphics.Tile;
import Logic.Board;
import Utilities.Timer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//inspiration https://www.javatpoint.com/first-javafx-application

public class TestJavaFX extends Application{
    public static final int TILE_SIZE = 80;
    public static final int NUM_OF_TILES = 64;
    public static final int SIDE_SIZE = 8;

    @Override
    public void start(Stage stage) throws Exception {
        Board board = new Board();

        Button newGame = new Button("New Game");
        Button exit = new Button("Exit");

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene scene = new Scene(createBoard());
                stage.setScene(scene);
                stage.setTitle("Arimaa");
                stage.show();
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        Rectangle rectangle = new Rectangle(100, 100, Color.GREEN);


        GridPane root = new GridPane();
//        StackPane root = new StackPane();
//        root.setAlignment(Pos.CENTER);
//        root.setHgap(10);
//        root.setVgap(10);

//        root.setPadding(new Insets(0, 10, 0, 10));
        root.add(newGame, 1, 1);
        root.add(exit, 1, 2);
//        root.add(rectangle, 0, 0);

        Scene scene = new Scene(root, 600,400);
//        Scene scene = new Scene(createBoard());

        stage.setScene(scene);
        stage.setTitle("Arimaa");
        stage.show();
    }

    private EventHandler<DragEvent> onDragOver = e -> {
        if(e.getSource() != e.getGestureSource()){
            e.acceptTransferModes(TransferMode.ANY);
            System.out.println("dragged to tile");
        }

        e.consume();
    };

    private EventHandler<DragEvent> onDragDone = e -> {
        ((Tile)e.getSource()).removeFigure();
    };

    GridPane createBoard(){
        GridPane gp = new GridPane();
        final Image im = new Image("Textures/camel_g.png");
        final ImageView imageView = new ImageView(im);

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
                        t.setFigureView((ImageView) dragEvent.getGestureSource());
//                        t.setFigureView((new ImageView(db.getImage())));

                        dragEvent.setDropCompleted(true);

                        dragEvent.consume();
                    }
                });
                t.setOnDragDone(onDragDone);
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
