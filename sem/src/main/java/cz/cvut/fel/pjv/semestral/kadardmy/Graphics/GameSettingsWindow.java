package cz.cvut.fel.pjv.semestral.kadardmy.Graphics;

import cz.cvut.fel.pjv.semestral.kadardmy.MainWindow.MainWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameSettingsWindow extends Stage {
    /**
     * Setup gamemode(vs computer or versus other player)
     * */

    MainWindow mainWindow;

    public GameSettingsWindow(MainWindow mainWindow){
        initSettings();
        this.mainWindow = mainWindow;
//        Scene scene = new GameScene(false, false, false);
    }

    protected void closeSettings(){
        this.close();
    }

    private void initSettings(){
        VBox allObj = new VBox();
        HBox golden = new HBox();
        HBox silver = new HBox();
        HBox buttons = new HBox();

        CheckBox aiGolden = new CheckBox();
        CheckBox aiSilver = new CheckBox();

        Label goldenLabel = new Label("Golden player AI");
        goldenLabel.setFont(Font.font("Impact", 15));
        Label silverLabel = new Label("Silver player AI");
        silverLabel.setFont(Font.font("Impact", 15));

        golden.getChildren().addAll(goldenLabel, aiGolden);
        golden.setAlignment(Pos.CENTER);
        golden.setSpacing(10);

        silver.getChildren().addAll(silverLabel, aiSilver);
        silver.setAlignment(Pos.CENTER);
        silver.setSpacing(10);

        Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainWindow.startGame(aiGolden.isSelected(), aiSilver.isSelected());
                closeSettings();
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        buttons.getChildren().addAll(exitButton, startButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);

        allObj.getChildren().addAll(golden, silver, buttons);
        allObj.setAlignment(Pos.CENTER);

        this.setScene(new Scene(allObj, 300, 100));
    }
}
