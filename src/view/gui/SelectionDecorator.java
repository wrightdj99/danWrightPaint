package view.gui;

import view.interfaces.IShape;
import view.interfaces.OneShape;

import java.awt.*;

public abstract class SelectionDecorator extends OneShape {

    /*public void setMyWidth(){

    }
    public void setMyHeight(){

    }*/
    //public abstract void setMyWidth();
    //public abstract void setMyHeight();
    protected OneShape decoratedShape;
    public SelectionDecorator(OneShape decoratedShape){
        this.decoratedShape = decoratedShape;
    }
    public void draw(Graphics2D graphics2D){
        decoratedShape.draw(graphics2D);
    }
    public OneShape CloneMe(){
        this.decoratedShape.CloneMe();
        return null;
    }
}
