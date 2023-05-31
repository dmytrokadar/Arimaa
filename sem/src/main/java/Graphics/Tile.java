package Graphics;

import Figures.Figure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Tile extends Pane{
    private Figure figure;
    private ImageView figureView = null;
    private Rectangle r;
    private int posX;
    private int posY;

    public Tile(Rectangle r, int posX, int posY) {
        this.r = r;
        this.posX = posX;
        this.posY = posY;

        getChildren().addAll(r);
    }

    public Figure getFigure(){
        return figure;
    }

    public void setFigureView(ImageView imageView) {
//        getChildren().remove(figureView);

        figureView = imageView;
        getChildren().add(figureView);
    }

    public void removeFigure(){
        figure = null;
        figureView = null;
    }


}
