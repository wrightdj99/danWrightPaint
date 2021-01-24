package view.interfaces;

import javax.swing.*;
import java.awt.*;


public abstract class PaintCanvasBase extends JComponent {
    public abstract Graphics2D getGraphics2D();
    public PaintCanvasBase(){
        this.StartPoint = new paintPoint();
        this.EndPoint = new paintPoint();
        this.MyClickHandler = new ClickHandler(this);
        this.addMouseListener(this.MyClickHandler);
    }
    public void drawDummyRectangle(){
        Graphics2D graphics2d = this.getGraphics2D();
        graphics2d.setColor(Color.green);
        int width = this.getPointWidth();
        int height = this.getPointHeight();
        graphics2d.fillRect(this.StartPoint.x, this.StartPoint.y, width, height);
    }
    private int getPointWidth(){
        int endWidth = this.EndPoint.x - this.StartPoint.x;
        if (endWidth < 0){
            endWidth = -1 * endWidth;
            this.StartPoint.x = this.StartPoint.x - endWidth;
        }
        return endWidth;
    }

    private int getPointHeight(){
        int endHeight = this.EndPoint.y - this.StartPoint.y;
        if (endHeight < 0){
            endHeight = -1 * endHeight;
            this.StartPoint.y = this.StartPoint.y - endHeight;
        }
        return endHeight;
    }


    private ClickHandler MyClickHandler;
    public paintPoint StartPoint;
    public paintPoint EndPoint;

}
