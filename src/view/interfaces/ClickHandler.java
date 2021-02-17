package view.interfaces;

import model.persistence.ApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {
    public ClickHandler(PaintCanvasBase _paintCanvasBase, undoRedo _myUr) {
        this.paintCanvasBase = _paintCanvasBase;
        this.myUr = _myUr;
    }

    private PaintCanvasBase paintCanvasBase;
    private ApplicationState myAppState;
    public undoRedo myUr;
    private OneShape myShape;
    private MoveRectangle myMoveRectangle;
    private SelectionRectangle mySelection;

    //private paintPoint StartPoint;
    //private paintPoint EndPoint;
    //private int ourWidth = StartPoint.x - EndPoint.x;
    //private int ourHeight = StartPoint.y - EndPoint.y;
    public void setMyAppState(ApplicationState aState) {
        this.myAppState = aState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintPoint startPoint = new paintPoint();
        startPoint.x = e.getX();
        startPoint.y = e.getY();
        //this.myRectangle = new Rectangle(startPoint);
        if (this.myAppState.getActiveMouseMode().name().equals("DRAW")) {
            if (this.myAppState.getActiveShapeType().name().equals("RECTANGLE")) {
                this.myShape = RectangleFactory.createRectangle(this.myAppState, startPoint);
            } else if (this.myAppState.getActiveShapeType().name().equals("TRIANGLE")) {
                this.myShape = TriangleFactory.createTriangle(this.myAppState, startPoint);
            } else {
                this.myShape = EllipseFactory.createEllipse(this.myAppState, startPoint);
            }
            this.myUr.unregisteredShapes.clear();
            System.out.println("My unregistered shapes: " + this.myUr.unregisteredShapes.size());
        } else if (this.myAppState.getActiveMouseMode().name().equals("SELECT")) {
            this.mySelection = SelectionFactory.CreateSelection(this.myAppState, startPoint);
            this.myUr.unregisteredShapes.clear();
            System.out.println("My unregistered shapes: " + this.myUr.unregisteredShapes.size());
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")) {
            this.myMoveRectangle = MoveFactory.createMove(this.myAppState, startPoint);
            System.out.println("Mouse move pressed");
            this.myUr.unregisteredShapes.clear();
            System.out.println("My unregistered shapes: " + this.myUr.unregisteredShapes.size());
        }
        //If statement for if it's an ellipse or a triangle
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        paintPoint endPoint = new paintPoint();
        endPoint.x = e.getX();
        endPoint.y = e.getY();
        if (this.myAppState.getActiveMouseMode().name().equals("DRAW")) {
            this.myShape.endPoint = endPoint;
            this.setShapeWidth((view.interfaces.myShape) this.myShape);
            this.setShapeHeight((view.interfaces.myShape) this.myShape);
            //this.myShape.setMyHeight();
            //this.myShape.setMyWidth();
            //System.out.println(endPoint.x);
            //this.myRectangle.setMyColor(Color.blue);
            this.myShape.draw(this.paintCanvasBase.getGraphics2D());
            myUr.registeredShapes.add(this.myShape);
            //System.out.println("ArrayList size " + myUr.registeredShapes.size());
            paintCanvasBase.clearItAll();
        } else if (this.myAppState.getActiveMouseMode().name().equals("SELECT")) {
            //System.out.println("This will be here soon");
            paintPoint startPoint;
            //this.mySelection.startPoint = startPoint;
            this.mySelection.endPoint = endPoint;
            if (this.mySelection.startPoint.y > this.mySelection.endPoint.y && this.mySelection.startPoint.x > this.mySelection.endPoint.x) {
                paintPoint newEndPoint = this.mySelection.startPoint;
                paintPoint newStartPoint = this.mySelection.endPoint;
                this.mySelection.endPoint = newEndPoint;
                this.mySelection.startPoint = newStartPoint;
            }
            this.mySelection.setMyHeight();
            this.mySelection.setMyWidth();
            //CODE IN QUESTION:
            for (OneShape shape : myUr.registeredShapes) {
                if (mySelection.startPoint.y < shape.startPoint.y && mySelection.endPoint.y > shape.endPoint.y) {
                    if (mySelection.startPoint.x < shape.startPoint.x && mySelection.endPoint.x > shape.endPoint.x) {
                        shape.isSelected = true;
                    }
                } else if (mySelection.startPoint.x > shape.startPoint.x && mySelection.endPoint.x < shape.endPoint.x) {
                    if (mySelection.startPoint.y > shape.startPoint.y && mySelection.endPoint.y < shape.endPoint.y) {
                        shape.isSelected = true;
                    }
                } else {
                    shape.isSelected = false;
                }
            }


        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")) {
            this.myMoveRectangle.endPoint = endPoint;
            this.myMoveRectangle.setMyHeight();
            this.myMoveRectangle.setMyWidth();
            myUr.registeredMoves.add(this.myMoveRectangle);

            for (OneShape shape : myUr.registeredShapes) {
                if (shape.isSelected == true) {
                    shape.startPoint.x = shape.startPoint.x + this.myMoveRectangle.width;
                    shape.startPoint.y = shape.startPoint.y + this.myMoveRectangle.height;
                }
            }

        } else {
            this.myShape.isSelected = false;
        }
        paintCanvasBase.drawAllShapes();

    }
    private void setShapeWidth(myShape theShape){
        theShape.setMyWidth();
    }
    private void setShapeHeight(myShape theShape){
        theShape.setMyHeight();
    }

}


