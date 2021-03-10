package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.*;

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
    private RectangleSelection mySelection;
    private MyGroup myGroup;
    private GroupedShape groupedShape;


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
        } else if (this.myAppState.getActiveMouseMode().name().equals("SELECT")) {
            this.mySelection = SelectionFactory.CreateSelection(this.myAppState, startPoint);
            this.myUr.unregisteredShapes.clear();
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")) {
            this.myMoveRectangle = MoveFactory.createMove(this.myAppState, startPoint);
            this.myUr.unregisteredShapes.clear();
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
            this.setShapeWidth((IShape) this.myShape);
            this.setShapeHeight((IShape) this.myShape);
            //this.myShape.setMyHeight();
            //this.myShape.setMyWidth();
            //this.myRectangle.setMyColor(Color.blue);
            //this.myShape.draw(this.paintCanvasBase.getGraphics2D());
            myUr.registeredShapes.add(this.myShape);
            paintCanvasBase.clearItAll();
        } else if (this.myAppState.getActiveMouseMode().name().equals("SELECT")) {
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
                    }else{
                        shape.isSelected = false;
                    }
                } else if (mySelection.startPoint.x > shape.startPoint.x && mySelection.endPoint.x < shape.endPoint.x) {
                    if (mySelection.startPoint.y > shape.startPoint.y && mySelection.endPoint.y < shape.endPoint.y) {
                        shape.isSelected = true;
                    }else{
                        shape.isSelected = false;
                    }
                } else {
                    shape.isSelected = false;
                }
                /*if (this shape is a GroupedShape){
                    call recursive method;
                }*/
                /*if(shape instanceof GroupedShape){
                    ShapeRecursion(groupedShape);
                }*/
            }
            for(OneShape shape : myUr.registeredShapes){
                if(shape.isSelected == true && shape instanceof GroupedShape){
                    shape.isSelected = true;
                    ShapeRecursion((GroupedShape) shape);
                }
            }
            for(OneShape shape : myUr.registeredShapes){
                if (shape.isSelected == true) {
                    SelectGroupsIAmPartOf(shape);
                }
            }
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")) {
            this.myMoveRectangle.endPoint = endPoint;
            this.myMoveRectangle.setMyHeight();
            this.myMoveRectangle.setMyWidth();
            myUr.registeredMoves.add(this.myMoveRectangle);

            for (OneShape shape : myUr.registeredShapes) {
                if (shape.isSelected == true) {
                    if(myAppState.getActiveShapeType().name().equals("TRIANGLE")){
                        /*int[] xPoints = {shape.startPoint.x + this.myMoveRectangle.width, shape.endPoint.x + this.myMoveRectangle.width, shape.startPoint.x + this.myMoveRectangle.width};
                        int[] yPoints = {shape.startPoint.y + this.myMoveRectangle.height, shape.endPoint.y + this.myMoveRectangle.height, shape.endPoint.y + this.myMoveRectangle.height};*/
                        shape.startPoint.x = shape.startPoint.x + this.myMoveRectangle.width;
                        shape.startPoint.y = shape.startPoint.y + this.myMoveRectangle.height;
                        shape.endPoint.x = shape.endPoint.x + this.myMoveRectangle.width;
                        shape.endPoint.y = shape.endPoint.y + this.myMoveRectangle.height;
                    }else{
                        shape.startPoint.x = shape.startPoint.x + this.myMoveRectangle.width;
                        shape.startPoint.y = shape.startPoint.y + this.myMoveRectangle.height;
                        shape.endPoint.x = shape.endPoint.x + this.myMoveRectangle.width;
                        shape.endPoint.y = shape.endPoint.y + this.myMoveRectangle.height;
                    }
                }
            }

        } else {
            this.myShape.isSelected = false;
        }
        paintCanvasBase.drawAllShapes();

    }
    /*private thing recursiveMethod(grouped shape){
        loop through registered shapes for this GroupedShape
            set each shape to selected
                if the registered shape is a GroupedShape, RECURSE (call this method again)
    }*/
    /*private void ShapeRecursion(GroupedShape grouped){
        for(OneShape shape : myUr.registeredShapes){
            if(shape instanceof GroupedShape){
                grouped.isSelected = true;
            }
        }
        ShapeRecursion(grouped);
    }*/
    private void SelectGroupsIAmPartOf(OneShape selectedShape){
        for (OneShape shape : myUr.registeredShapes) {
            if (shape instanceof GroupedShape){

                GroupedShape groupedShape = (GroupedShape)shape;
                if (groupedShape.MyShapes.contains(selectedShape)){
                    ShapeRecursion(groupedShape);
                }
            }
        }

    }
    private void ShapeRecursion(GroupedShape gS){
        gS.isSelected = true;
        for(OneShape shape : gS.MyShapes){
            shape.isSelected = true;
            if(shape instanceof GroupedShape){
                ShapeRecursion((GroupedShape) shape);
            }
        }
    }
    private void setShapeWidth(IShape theShape){
        theShape.setMyWidth();
    }
    private void setShapeHeight(IShape theShape){
        theShape.setMyHeight();
    }

}


