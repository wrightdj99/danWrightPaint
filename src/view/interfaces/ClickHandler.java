package view.interfaces;
import model.persistence.ApplicationState;

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
    private OneShape myShape;
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
        //If Statement for if it's a rectangle
        if(this.myAppState.getActiveShapeType().name().equals("RECTANGLE")){
            this.myShape = RectangleFactory.createRectangle(this.myAppState, startPoint);
        }
        else{
            this.myShape = EllipseFactory.createEllipse(this.myAppState, startPoint);
        }
        //If statement for if it's an ellipse or a triangle
    }

    @Override
    public void mouseReleased(MouseEvent e){
        paintPoint endPoint = new paintPoint();
        endPoint.x = e.getX();
        endPoint.y = e.getY();
        this.myShape.endPoint = endPoint;
        this.myShape.setMyHeight();
        this.myShape.setMyWidth();
        //this.myRectangle.setMyColor(Color.blue);
        this.myShape.draw(this.paintCanvasBase.getGraphics2D());
        myUr.registeredShapes.add(this.myShape);
        System.out.println("ArrayList size " + myUr.registeredShapes.size());
        paintCanvasBase.clearItAll();
        //myUr.registeredRect.add(myRect);
        //this.myUr.registeredRect.add(myRect);
        //myUr.registeredRect.add(myRect);
        //this.paintCanvasBase.drawDummyRectangle();
        //e.translatePoint(EndPoint.x, EndPoint.y);
    }

}
