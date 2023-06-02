package cz.cvut.fel.pjv.semestral.kadardmy.Utilities;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

public class UpdateTime implements ChangeListener<String> {

    Label timeG;

    public UpdateTime(Label timeL){
        timeG = timeL;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        if(!t1.equals("")){
            long time = Long.parseLong(t1);
            timeG.setText(convertLongToTime(time));
        }
    }

    public String convertLongToTime(long time){
        return String.format("%02d:%02d", (time / 60) % 60, time % 60);
    }
}
