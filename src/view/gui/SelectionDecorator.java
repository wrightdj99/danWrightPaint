package view.gui;

import view.interfaces.IShape;
import view.interfaces.OneShape;

import java.awt.*;
import java.util.ArrayList;

public abstract class SelectionDecorator extends OneShape {

    /*public void setMyWidth(){

    }
    public void setMyHeight(){

    }*/
    //public abstract void setMyWidth();
    //public abstract void setMyHeight();
    protected OneShape decoratedShape;
    public boolean AmIPartOfAGroup;
    public SelectionDecorator(OneShape decoratedShape, boolean _amIPartOfAGroup){
        this.decoratedShape = decoratedShape;
        this.AmIPartOfAGroup = _amIPartOfAGroup;
    }
    public void draw(Graphics2D graphics2D, ArrayList<OneShape> registeredShapes){
        decoratedShape.draw(graphics2D, registeredShapes);
    }
    public OneShape CloneMe(){
        this.decoratedShape.CloneMe();
        return null;
    }
}
