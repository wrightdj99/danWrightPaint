package view.interfaces;

import java.awt.*;

public abstract class OneShape {
    public paintPoint startPoint, endPoint;
    protected int width, height;
    public abstract void draw(Graphics2D graphics2d);
    public abstract void setMyWidth();
    public abstract void setMyHeight();
}
