package view.interfaces;

import view.gui.GroupedShape;
import view.gui.paintPoint;

import java.awt.*;
import java.util.ArrayList;

public abstract class OneShape {
    public paintPoint startPoint, endPoint;
    public boolean isSelected;
    public boolean IsCopied;
    public int width, height;
    public abstract void draw(Graphics2D graphics2d, ArrayList<OneShape> registeredShapes);
    public abstract OneShape CloneMe();
    protected boolean CheckIfIAmPartOfAGroup(ArrayList<OneShape> registeredShapes){
        for (OneShape shape : registeredShapes) {
            if (shape instanceof GroupedShape){

                GroupedShape groupedShape = (GroupedShape)shape;
                if (groupedShape.MyShapes.contains(this)){
                    return true;
                }
            }
        }
        return false;
    }

    //public abstract void setMyWidth();
    //public abstract void setMyHeight();
}
