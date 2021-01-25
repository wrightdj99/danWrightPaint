package view.interfaces;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {
    public ClickHandler(PaintCanvasBase _paintCanvasBase, undoRedo _myUr){
        this.paintCanvasBase = _paintCanvasBase;
        this.myUr = _myUr;
    }
    private PaintCanvasBase paintCanvasBase;
    public undoRedo myUr;
    private Rectangle myRectangle;
    //private paintPoint StartPoint;
    //private paintPoint EndPoint;
    //private int ourWidth = StartPoint.x - EndPoint.x;
    //private int ourHeight = StartPoint.y - EndPoint.y;
    @Override
    public void mousePressed(MouseEvent e){
        paintPoint startPoint = new paintPoint();
        startPoint.x = e.getX();
        startPoint.y = e.getY();
        this.myRectangle = new Rectangle(startPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        paintPoint endPoint = new paintPoint();
        endPoint.x = e.getX();
        endPoint.y = e.getY();
        this.myRectangle.endPoint = endPoint;
        this.myRectangle.draw(this.paintCanvasBase.getGraphics2D());
        myUr.registeredRect.add(this.myRectangle);
        System.out.println("ArrayList size " + myUr.registeredRect.size());
        //myUr.registeredRect.add(myRect);
        //this.myUr.registeredRect.add(myRect);
        //myUr.registeredRect.add(myRect);
        //this.paintCanvasBase.drawDummyRectangle();
        //e.translatePoint(EndPoint.x, EndPoint.y);
    }

}
