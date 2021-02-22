package view.gui;

import model.persistence.ApplicationState;
import view.gui.MoveRectangle;
import view.interfaces.OneShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class undoRedo {

    public undoRedo(PaintCanvasBase _pCV){
        this.paintCanvasBase = _pCV;
        this.registeredShapes =  new ArrayList<OneShape>();
        this.unregisteredShapes = new ArrayList<OneShape>();
        this.registeredMoves = new ArrayList<MoveRectangle>();
        this.unregisteredMoves = new ArrayList<MoveRectangle>();
    }

    public ApplicationState myAppState;
    private PaintCanvasBase paintCanvasBase;
    public ArrayList<OneShape> registeredShapes;
    public ArrayList<OneShape> unregisteredShapes;
    public ArrayList<MoveRectangle> registeredMoves;
    public ArrayList<MoveRectangle> unregisteredMoves;

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
                System.out.println("My unregistered shapes: " + this.unregisteredShapes.size());
            }
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")){
            if(this.registeredMoves.size() == 0){
                System.out.println("Registered Moves is empty");
            }
            else{
                MoveRectangle undoMove = this.registeredMoves.remove(this.registeredMoves.size() - 1);
                this.unregisteredMoves.add(undoMove);
                for (OneShape shape : this.registeredShapes) {
                    if (shape.isSelected == true) {
                        if(this.myAppState.getActiveShapeType().name().equals("TRIANGLE")){
                            shape.startPoint.x = shape.startPoint.x - undoMove.width;
                            shape.startPoint.y = shape.startPoint.y - undoMove.height;
                            shape.endPoint.x = shape.endPoint.x - undoMove.width;
                            shape.endPoint.y = shape.endPoint.y - undoMove.height;
                        }
                        else{
                            shape.startPoint.x = shape.startPoint.x - undoMove.width;
                            shape.startPoint.y = shape.startPoint.y - undoMove.height;
                        }
                    }
                }

            }
        }

        else if(this.myAppState.getActiveMouseMode().name().equals("SELECT")){
            ArrayList<OneShape> shapesToReturn = new ArrayList<OneShape>();
            for(OneShape MyOneShape : this.unregisteredShapes){
                shapesToReturn.add(MyOneShape);
            }

            for(OneShape MyOneShape : shapesToReturn){
                unregisteredShapes.remove(MyOneShape);
                registeredShapes.add(MyOneShape);
            }
        }


        paintCanvasBase.drawAllShapes();


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
                reinsertedShape.draw(paintCanvasBase.getGraphics2D());
            }
        } else if (this.myAppState.getActiveMouseMode().name().equals("MOVE")) {
            if (this.unregisteredMoves.size() == 0) {
                System.out.println("Unregistered Moves is empty");
            } else {
                MoveRectangle redoMove = this.unregisteredMoves.remove(this.unregisteredMoves.size() - 1);
                this.registeredMoves.add(redoMove);
                for (OneShape shape : this.registeredShapes) {
                    if (shape.isSelected == true) {
                        if(this.myAppState.getActiveShapeType().name().equals("TRIANGLE")){
                            shape.startPoint.x = shape.startPoint.x + redoMove.width;
                            shape.startPoint.y = shape.startPoint.y + redoMove.height;
                            shape.endPoint.x = shape.endPoint.x + redoMove.width;
                            shape.endPoint.y = shape.endPoint.y + redoMove.height;
                        }
                        else{
                            shape.startPoint.x = shape.startPoint.x + redoMove.width;
                            shape.startPoint.y = shape.startPoint.y + redoMove.height;
                        }
                    }
                }
            }

            paintCanvasBase.drawAllShapes();
        }

        else if(this.myAppState.getActiveMouseMode().name().equals("SELECT")){
            System.out.println("Entering the method here ");
            ArrayList<OneShape> shapesToDelete = new ArrayList<OneShape>();
            for(OneShape MyOneShape : this.registeredShapes){
                if(MyOneShape.isSelected == true){
                    shapesToDelete.add(MyOneShape);
                }
            }
            for(OneShape MyOneShape : shapesToDelete){
                registeredShapes.remove(MyOneShape);
                unregisteredShapes.add(MyOneShape);
            }
            paintCanvasBase.drawAllShapes();
        }
    }
}
