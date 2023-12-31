package cz.cvut.fel.pjv.semestral.kadardmy.MainWindow;
import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Camel;
import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Cat;
import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Figure;
import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.FigureView;
import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.GameScene;
import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.GameSettingsWindow;
import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.Tile;
import cz.cvut.fel.pjv.semestral.kadardmy.Logic.Board;
import cz.cvut.fel.pjv.semestral.kadardmy.Utilities.Timer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//inspiration https://www.javatpoint.com/first-javafx-application

public class MainWindow extends Application{
    public static final int TILE_SIZE = 80;
    public static final int NUM_OF_TILES = 64;
    public static final int SIDE_SIZE = 8;

    private Stage stage;

    /**
     * Open window with game
     *
     * @param aiG - is Golden player AI
     * @param aiS - is Silver player AI
     * */
    public void startGame(boolean aiG, boolean aiS){
        Scene scene = new GameScene(false, aiG, aiS);
        stage.setScene(scene);
        stage.setTitle("Arimaa");
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Stage settingsWindow = new GameSettingsWindow(this);

        stage.minWidthProperty().bind(stage.heightProperty().multiply(1.5));
        stage.maxWidthProperty().bind(stage.heightProperty().multiply(1.7));

        Button newGame = new Button("New Game");
        Button loadGame = new Button("Load Game");
        Button exit = new Button("Exit");

        Label title = new Label("Arimaa");
        title.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 100));

        StackPane titlePane = new StackPane();
        titlePane.getChildren().add(title);
        titlePane.setAlignment(Pos.CENTER);

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                settingsWindow.show();
                settingsWindow.setTitle("Settings");
//                stage.setScene(scene);
//                stage.setTitle("Settings");
//                stage.show();

//                Scene scene = new GameScene(false, false, false);
//                stage.setScene(scene);
//                stage.setTitle("Arimaa");
//                stage.show();
            }
        });

        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene scene = new GameScene(true, false, false);
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

        BorderPane root = new BorderPane();
//        StackPane root = new StackPane();
//        root.setAlignment(Pos.CENTER);
//        root.setHgap(10);
//        root.setVgap(10);

//        root.setPadding(new Insets(0, 10, 0, 10));
        VBox buttonBox = new VBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        buttonBox.getChildren().addAll(newGame, loadGame, exit);

        root.setTop(titlePane);
        root.setCenter(buttonBox);
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
