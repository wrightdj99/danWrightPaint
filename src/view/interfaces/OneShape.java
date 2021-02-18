package view.interfaces;

import view.gui.paintPoint;

import java.awt.*;

public abstract class OneShape {
    public paintPoint startPoint, endPoint;
    public boolean isSelected;
    public boolean IsCopied;
    protected int width, height;
    public abstract void draw(Graphics2D graphics2d);
    public abstract OneShape CloneMe();
    //public abstract void setMyWidth();
    //public abstract void setMyHeight();
}
