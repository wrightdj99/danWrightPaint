package view.interfaces;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {
    public ClickHandler(PaintCanvasBase _paintCanvasBase){
        this.paintCanvasBase = _paintCanvasBase;
    }
    private PaintCanvasBase paintCanvasBase;
    //private paintPoint StartPoint;
    //private paintPoint EndPoint;
    //private int ourWidth = StartPoint.x - EndPoint.x;
    //private int ourHeight = StartPoint.y - EndPoint.y;
    @Override
    public void mousePressed(MouseEvent e){
        this.paintCanvasBase.StartPoint.x = e.getX();
        this.paintCanvasBase.StartPoint.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        this.paintCanvasBase.EndPoint.x = e.getX();
        this.paintCanvasBase.EndPoint.y = e.getY();
        this.paintCanvasBase.drawDummyRectangle();
        //e.translatePoint(EndPoint.x, EndPoint.y);
    }

}
