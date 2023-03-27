package MainWindow;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private static int width;
    private static int height;

    public GamePanel(int width, int height){

        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();

        //TODO make buttons work
        JButton newGameButton = new JButton("New Game");
        newGameButton.setVerticalAlignment(AbstractButton.BOTTOM);
        newGameButton.setHorizontalAlignment(AbstractButton.CENTER);
        newGameButton.setEnabled(true);

        JButton exitButton = new JButton("Exit");
        exitButton.setVerticalAlignment(AbstractButton.CENTER);
        exitButton.setHorizontalAlignment(AbstractButton.CENTER);

    }

    @Override
    public void run() {

    }
}
