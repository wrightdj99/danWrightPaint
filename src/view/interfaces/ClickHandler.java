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
    private SelectionRectangle mySelection;
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
        if(this.myAppState.getActiveMouseMode().name().equals("DRAW")) {
            if(this.myAppState.getActiveShapeType().name().equals("RECTANGLE")){
                this.myShape = RectangleFactory.createRectangle(this.myAppState, startPoint);
            }
            else if(this.myAppState.getActiveShapeType().name().equals("TRIANGLE")){
                this.myShape = TriangleFactory.createTriangle(this.myAppState, startPoint);
            }
            else{
                this.myShape = EllipseFactory.createEllipse(this.myAppState, startPoint);
            }
        }

        else if(this.myAppState.getActiveMouseMode().name().equals("SELECT")){
            this.mySelection = SelectionFactory.CreateSelection(this.myAppState, startPoint);
        }

        else{
            System.out.println("Not allowed");
        }
        //If statement for if it's an ellipse or a triangle
    }

    @Override
    public void mouseReleased(MouseEvent e){
        paintPoint endPoint = new paintPoint();
        endPoint.x = e.getX();
        endPoint.y = e.getY();
        if(this.myAppState.getActiveMouseMode().name().equals("DRAW")){
            this.myShape.endPoint = endPoint;
            this.myShape.setMyHeight();
            this.myShape.setMyWidth();
            //System.out.println(endPoint.x);
            //this.myRectangle.setMyColor(Color.blue);
            this.myShape.draw(this.paintCanvasBase.getGraphics2D());
            myUr.registeredShapes.add(this.myShape);
            System.out.println("ArrayList size " + myUr.registeredShapes.size());
            paintCanvasBase.clearItAll();
        }
        else if(this.myAppState.getActiveMouseMode().name().equals("SELECT")){
            //System.out.println("This will be here soon");
            paintPoint startPoint;
            //this.mySelection.startPoint = startPoint;
            this.mySelection.endPoint = endPoint;
            if (this.mySelection.startPoint.y > this.mySelection.endPoint.y && this.mySelection.startPoint.x > this.mySelection.endPoint.x){
                paintPoint newEndPoint = this.mySelection.startPoint;
                paintPoint newStartPoint = this.mySelection.endPoint;
                this.mySelection.endPoint = newEndPoint;
                this.mySelection.startPoint = newStartPoint;
            }
            this.mySelection.setMyHeight();
            this.mySelection.setMyWidth();
            //CODE IN QUESTION:
            for(OneShape shape : myUr.registeredShapes){
                if(mySelection.startPoint.y < shape.startPoint.y && mySelection.endPoint.y > shape.endPoint.y){
                    if(mySelection.startPoint.x < shape.startPoint.x && mySelection.endPoint.x > shape.endPoint.x){
                        shape.isSelected = true;
                    }

                }
                /*
                if(mySelection.startPoint.y < shape.endPoint.y){
                    if(mySelection.startPoint.x < shape.endPoint.x){
                        shape.isSelected = true;
                    }
                }
                 */
                else{
                    shape.isSelected = false;
                }
            //-----------------------
                /*if(mySelection.startPoint.x >= shape.endPoint.x && shape.endPoint.x >= mySelection.startPoint.x){
                    //System.out.println("Intersected x");
                    shape.isSelected = true;
                    System.out.println("Selectedx");
                    //System.out.println();*/
                }
                /*if(mySelection.endPoint.x <= shape.endPoint.x){
                    shape.isSelected = true;
                }
                if(mySelection.endPoint.y >= shape.endPoint.y){
                    //System.out.println("Intersection y");
                    shape.isSelected = true;
                    System.out.println("Selectedy");
                }
                if(mySelection.endPoint.y <= shape.endPoint.y){
                    shape.isSelected = true;
                }*/
            }
            paintCanvasBase.drawAllRectangles();
            //System.out.println(endPoint.x);
            //System.out.println("This is my height: " + mySelection.height + "This is my width: " + mySelection.width);
            //System.out.println("This is my end point: " + this.mySelection.endPoint);

        }

        /*this.myShape.endPoint = endPoint;
        this.myShape.setMyHeight();
        this.myShape.setMyWidth();
        //this.myRectangle.setMyColor(Color.blue);
        this.myShape.draw(this.paintCanvasBase.getGraphics2D());
        myUr.registeredShapes.add(this.myShape);
        System.out.println("ArrayList size " + myUr.registeredShapes.size());
        paintCanvasBase.clearItAll();*/
        //myUr.registeredRect.add(myRect);
        //this.myUr.registeredRect.add(myRect);
        //myUr.registeredRect.add(myRect);
        //this.paintCanvasBase.drawDummyRectangle();
        //e.translatePoint(EndPoint.x, EndPoint.y);
    }


