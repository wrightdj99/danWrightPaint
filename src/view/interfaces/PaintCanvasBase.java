package view.interfaces;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public abstract class PaintCanvasBase extends JComponent {
    public abstract Graphics2D getGraphics2D();
    public PaintCanvasBase(){
        //this.StartPoint = new paintPoint();
        //this.EndPoint = new paintPoint();
        this.myUndoRedo = new undoRedo(this);
        this.MyClickHandler = new ClickHandler(this, this.myUndoRedo);
        this.addMouseListener(this.MyClickHandler);
        //this.myUR = new undoRedo(this);
    }
    public void drawAllRectangles(){
        Graphics2D graphics2D = this.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int i =0; i < this.myUndoRedo.registeredRect.size(); i++){
            Rectangle rectangle = this.myUndoRedo.registeredRect.get(i);
            rectangle.draw(graphics2D);
        }

    }

    public void clearItAll(){
        this.myUndoRedo.unregisteredRect.clear();
    }

    /*public void redrawRectangle(){
        Graphics2D graphics2D = this.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        for(int i = 0; i < this.myUndoRedo.unregisteredRect.size(); i++){
            Rectangle redoRectangle = this.myUndoRedo.unregisteredRect.get(i);
            if(i == this.myUndoRedo.unregisteredRect.size() - 1){
                redoRectangle.draw(graphics2D);
            }
        }


    }*/

    //ArrayList<Rectangle> registeredRect = new ArrayList<Rectangle>();

    /*
    public void drawDummyRectangle(){
        Graphics2D graphics2d = this.getGraphics2D();
        graphics2d.setColor(Color.green);
        int width = this.getPointWidth();
        int height = this.getPointHeight();
        graphics2d.fillRect(this.StartPoint.x, this.StartPoint.y, width, height);
    }

    //myShape myRect = new Rectangle(24, 90);
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
    */
    private ClickHandler MyClickHandler;
    public undoRedo myUndoRedo;
    //public undoRedo myUR;
    //public paintPoint StartPoint;
    //public paintPoint EndPoint;

}
