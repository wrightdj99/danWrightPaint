package view.interfaces;

import model.persistence.ApplicationState;

import javax.swing.*;
import java.awt.*;


public abstract class PaintCanvasBase extends JComponent {
    public abstract Graphics2D getGraphics2D();
    //public ApplicationState myAppState;
    public PaintCanvasBase(){
        //this.StartPoint = new paintPoint();
        //this.EndPoint = new paintPoint();
        //this.myAppState = _appState;
        this.myUndoRedo = new undoRedo(this);
        this.MyClickHandler = new ClickHandler(this, this.myUndoRedo);
        this.addMouseListener(this.MyClickHandler);
        //this.myUR = new undoRedo(this);
    }
    public void drawAllShapes(){
        Graphics2D graphics2D = this.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int i = 0; i < this.myUndoRedo.registeredShapes.size(); i++){
            OneShape shape = this.myUndoRedo.registeredShapes.get(i);
            if (this.myAppState.getActiveMouseMode().name().equals("DRAW")){
                shape.isSelected = false;
            }
            shape.draw(graphics2D);
        }

    }

    public void setMyAppState(ApplicationState aState){
        this.myAppState = aState;
        this.myUndoRedo.setMyAppState(aState);
    }

    public void clearItAll(){
        this.myUndoRedo.unregisteredShapes.clear();
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
    public ClickHandler GetMyClickHandler(){
        return this.MyClickHandler;
    }
    private ClickHandler MyClickHandler;
    public undoRedo myUndoRedo;
    public ApplicationState myAppState;
    //public undoRedo myUR;
    //public paintPoint StartPoint;
    //public paintPoint EndPoint;

}
