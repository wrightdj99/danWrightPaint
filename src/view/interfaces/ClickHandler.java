package view.interfaces;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {
    public ClickHandler(PaintCanvasBase _paintCanvasBase, undoRedo _myUr){
        this.paintCanvasBase = _paintCanvasBase;
        this.myUr = _myUr;
    }
    private PaintCanvasBase paintCanvasBase;
    private ApplicationState myAppState;
    public undoRedo myUr;
    private Rectangle myRectangle;
    //private paintPoint StartPoint;
    //private paintPoint EndPoint;
    //private int ourWidth = StartPoint.x - EndPoint.x;
    //private int ourHeight = StartPoint.y - EndPoint.y;
    public void setMyAppState(ApplicationState aState){
        this.myAppState = aState;
    }
    @Override
    public void mousePressed(MouseEvent e){
        paintPoint startPoint = new paintPoint();
        startPoint.x = e.getX();
        startPoint.y = e.getY();
        //this.myRectangle = new Rectangle(startPoint);
        this.myRectangle = RectangleFactory.createRectangle(this.myAppState, startPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        paintPoint endPoint = new paintPoint();
        endPoint.x = e.getX();
        endPoint.y = e.getY();
        this.myRectangle.endPoint = endPoint;
        this.myRectangle.setMyHeight();
        this.myRectangle.setMyWidth();
        //this.myRectangle.setMyColor(Color.blue);
        this.myRectangle.draw(this.paintCanvasBase.getGraphics2D());
        myUr.registeredRect.add(this.myRectangle);
        System.out.println("ArrayList size " + myUr.registeredRect.size());
        paintCanvasBase.clearItAll();
        //myUr.registeredRect.add(myRect);
        //this.myUr.registeredRect.add(myRect);
        //myUr.registeredRect.add(myRect);
        //this.paintCanvasBase.drawDummyRectangle();
        //e.translatePoint(EndPoint.x, EndPoint.y);
    }

}
