package view.interfaces;

import model.persistence.ApplicationState;

import java.util.ArrayList;

public class undoRedo {

    public undoRedo(PaintCanvasBase _pCV){
        this.paintCanvasBase = _pCV;
        this.registeredShapes =  new ArrayList<OneShape>();
        this.unregisteredShapes = new ArrayList<OneShape>();
        this.registeredMoves = new ArrayList<MoveRectangle>();
        this.unregisteredMoves = new ArrayList<MoveRectangle>();
        //this.unregisteredEllipse = new ArrayList<Ellipse>();
        //this.myShape = myAppState.getActiveShapeType();
        //this.myShapeMap = new ShapeTypeMap();
        //this.myAppState = _appState;
    }
    //Need these for triangles and ellipses
    public ArrayList<OneShape> registeredShapes;
    public ArrayList<OneShape> unregisteredShapes;
    public ArrayList<MoveRectangle> registeredMoves;
    public ArrayList<MoveRectangle> unregisteredMoves;
    //public ArrayList<Ellipse> unregisteredEllipse;
    //public ShapeType myShape;
    //public ShapeTypeMap myShapeMap;
    public ApplicationState myAppState;

    PaintCanvasBase paintCanvasBase;
    public void setMyAppState(ApplicationState aState){
        this.myAppState = aState;
    }

    public void myUndo(){
        if (this.myAppState.getActiveMouseMode().name().equals("DRAW")) {
            if (this.registeredShapes.size() == 0) {
                System.out.println("Registered Shapes is empty");
            } else {
                //System.out.println("Before " + this.registeredRect.size());
                OneShape removedShape = this.registeredShapes.remove(this.registeredShapes.size() - 1);
                this.unregisteredShapes.add(removedShape);
                System.out.println("Done" + this.unregisteredShapes.size());
                //System.out.println("After " + this.registeredRect.size());
            }
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")){
            if(this.registeredMoves.size() == 0){
                System.out.println("Registered Moves is empty");
            }
            else{
                //System.out.println("Before " + this.registeredRect.size());
                MoveRectangle undoMove = this.registeredMoves.remove(this.registeredMoves.size() - 1);
                this.unregisteredMoves.add(undoMove);
                for (OneShape shape : this.registeredShapes) {
                    if (shape.isSelected == true) {
                        shape.startPoint.x = shape.startPoint.x - undoMove.width;
                        shape.startPoint.y = shape.startPoint.y - undoMove.height;
                    }
                }

                //System.out.println("After " + this.registeredRect.size());
            }
        }
        paintCanvasBase.drawAllShapes();


        //System.out.println("Our size " + this.registeredRect.size());
        //If statement for button being pressed then...
        /*if(registeredRect.size() > 0){
            Rectangle removedRectangle = this.registeredRect.remove(this.registeredRect.size() - 1);
            this.unregisteredRect.add(removedRectangle);
            //System.out.println("Size of registeredRect is: " + .registeredRect.size());
        }*/

        //System.out.println("Size of registeredRect is: " + this.registeredRect.size());

        //Rectangle removedRectangle = this.registeredRect.remove(this.registeredRect.size() - 1);
        //this.unregisteredRect.add(removedRectangle);
    }

    public void myRedo() {
        //System.out.println("Size of unregisteredRect is: " + this.unregisteredRect.size());
        /*if(this.unregisteredRect.size() > 0){
            Rectangle reintroduedRectangle = this.unregisteredRect.remove(this.unregisteredRect.size() - 1);
            this.registeredRect.add(reintroduedRectangle);
            reintroduedRectangle.draw(paintCanvasBase.getGraphics2D());
        }
        System.out.println("Size of unregisteredRect is: " + this.unregisteredRect.size());*/
        //Rectangle reintroduedRectangle = this.unregisteredRect.remove(this.unregisteredRect.size() - 1);
        //this.registeredRect.add(reintroduedRectangle);
        if (this.myAppState.getActiveMouseMode().name().equals("DRAW")) {
            if (this.unregisteredShapes.size() == 0) {
                System.out.println("Unregistered Shapes is empty");
            } else {
                OneShape reinsertedShape = this.unregisteredShapes.remove(this.unregisteredShapes.size() - 1);
                this.registeredShapes.add(reinsertedShape);
                System.out.println("Exiled rectangles " + this.unregisteredShapes.size());
                System.out.println("Redeemed rectangles " + this.registeredShapes.size());
            }
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")) {
            if (this.unregisteredMoves.size() == 0) {
                System.out.println("Unregistered Moves is empty");
            } else {
                //System.out.println("Before " + this.registeredRect.size());
                MoveRectangle redoMove = this.unregisteredMoves.remove(this.unregisteredMoves.size() - 1);
                this.registeredMoves.add(redoMove);
                for (OneShape shape : this.registeredShapes) {
                    if (shape.isSelected == true) {
                        shape.startPoint.x = shape.startPoint.x + redoMove.width;
                        shape.startPoint.y = shape.startPoint.y + redoMove.height;
                    }
                }
            }

            paintCanvasBase.drawAllShapes();
        }
    }
}
