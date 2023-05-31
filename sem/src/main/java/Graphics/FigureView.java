package Graphics;

import Figures.Figure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FigureView extends ImageView {
    private int posX;
    private int posY;
    private Figure figure;

    public FigureView(int posX, int posY, Figure figure) {
        this.posX = posX;
        this.posY = posY;
        this.figure = figure;
    }

    public FigureView(String s, int posX, int posY, Figure figure) {
        super(s);
        this.posX = posX;
        this.posY = posY;
        this.figure = figure;
    }

    public FigureView(Image image, int posX, int posY, Figure figure) {
        super(image);
        this.posX = posX;
        this.posY = posY;
        this.figure = figure;
    }
}
