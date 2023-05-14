package MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Button newGame = new Button("New Game");
        Button exit = new Button("Exit");

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

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
//        root.add(newGame, 1, 1);
//        root.add(exit, 1, 2);
//        root.add(rectangle, 0, 0);

//        Scene scene = new Scene(root, 600,400);
        Scene scene = new Scene(createBoard());

        stage.setScene(scene);
        stage.setTitle("Arimaa");
        stage.show();
    }

    GridPane createBoard(){
        GridPane gp = new GridPane();

        for (int i = 0; i < SIDE_SIZE; i++){
            for (int j = 0; j < SIDE_SIZE; j++){
                Rectangle r = new Rectangle(TILE_SIZE, TILE_SIZE, Color.WHITE);
                if((i == 2 && (j == 2 || j == 5)) || (i == 5 && (j == 2 || j == 5))){
                    r.setFill(Color.GREEN);
                }
                r.setStroke(Color.BLACK);
                gp.add(r, i, j);
            }
        }

        return gp;
    }
}
