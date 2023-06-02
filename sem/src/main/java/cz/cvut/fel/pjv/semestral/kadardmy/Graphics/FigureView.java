package cz.cvut.fel.pjv.semestral.kadardmy.Graphics;

import cz.cvut.fel.pjv.semestral.kadardmy.Figures.Figure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FigureView extends ImageView {
    private int posX;
    private int posY;
    private Figure figure;

    /**
     * Class that binds Tiles with cz.cvut.fel.pjv.semestral.kadardmy.Figures, contains Figure image
     *
     * @param figure - figure
     * */
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

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Figure getFigure() {
        return figure;
    }
}
