package Utilities;

import Logic.Board;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.sql.Time;

public class Timer {
    /*
    * Timer for every player
    * */
    private static final int SECONDS = 600;

    public Board.Color getColor() {
        return color;
    }

    private boolean enabled = false;
    private Board.Color color;

    public boolean isEnabled() {
        return enabled;
    }

    private long time = 0;
    private long enabledStartTime = 0;


    public Timer(Board.Color color) {
        time = SECONDS;
        this.color = color;
    }

    public void startTimer(){
        if(!enabled){
            enabled = true;
            enabledStartTime = System.currentTimeMillis();
            service.restart();
        }
    }

    public void pauseTimer(){
        enabled = false;
    }

    public StringProperty getProperty() {
        return (StringProperty) service.messageProperty();
    }

    Service<Void> service = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (enabled){
                        long localTime = System.currentTimeMillis();
                        if(localTime - enabledStartTime >= 1000){
                            time--;
                            enabledStartTime = localTime;
                            updateMessage(String.valueOf(time));
                            if(time == 0){
                                pauseTimer();
                            }
                            try {
                                Thread.sleep(40);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    return null;
                }
            };
        }
    };
}
