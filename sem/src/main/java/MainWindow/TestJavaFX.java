package MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//inspiration https://www.javatpoint.com/first-javafx-application

public class TestJavaFX extends Application{
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



        StackPane root=new StackPane();
        root.getChildren().add(newGame);
        root.getChildren().add(exit);

        Scene scene = new Scene(root, 600,400);

        stage.setScene(scene);
        stage.setTitle("Arimaa");
        stage.show();
    }
}
