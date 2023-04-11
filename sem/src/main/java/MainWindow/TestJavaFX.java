package MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//inspiration https://www.javatpoint.com/first-javafx-application

public class TestJavaFX extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Button btn = new Button("Start Game");

        StackPane root=new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("First JavaFX Application");
        stage.show();
    }
}
