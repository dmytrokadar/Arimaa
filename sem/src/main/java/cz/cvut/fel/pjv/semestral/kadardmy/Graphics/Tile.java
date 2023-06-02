package cz.cvut.fel.pjv.semestral.kadardmy.Graphics;

import cz.cvut.fel.pjv.semestral.kadardmy.Graphics.FigureView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Tile extends Pane{
    private FigureView figureView = null;
    private Rectangle r;
    private int posX;
    private int posY;

    /**
     * Every tile that is rendered on the board. Can contain FigureView
     *
     * @param r - rectangle for background
     */
    public Tile(Rectangle r, int posX, int posY) {
        this.r = r;
        this.posX = posX;
        this.posY = posY;

        getChildren().addAll(r);
    }

    /**
     * Adds FigureView to Tile
     *
     * @param imageView - FigureView with picture and figure
     * */
    public void setFigureView(FigureView imageView) {
//        getChildren().remove(figureView);

        figureView = imageView;
        getChildren().add(figureView);
    }

    /**
     * Removes FigureView from Tile
     *
     *
     * */
    public void removeFigure(){
        if(figureView != null)
            getChildren().remove(figureView);
        figureView = null;
    }

    public FigureView getFigureView() {
        return figureView;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
