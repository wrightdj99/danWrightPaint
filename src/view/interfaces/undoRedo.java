package view.interfaces;

import model.ShapeType;
import model.ShapeTypeMap;
import model.persistence.ApplicationState;

import java.util.ArrayList;

public class undoRedo {

    public undoRedo(PaintCanvasBase _pCV){
        this.paintCanvasBase = _pCV;
        this.registeredShapes =  new ArrayList<OneShape>();
        this.unregisteredShapes = new ArrayList<OneShape>();
        //this.registeredEllipse = new ArrayList<Ellipse>();
        //this.unregisteredEllipse = new ArrayList<Ellipse>();
        //this.myShape = myAppState.getActiveShapeType();
        //this.myShapeMap = new ShapeTypeMap();
        //this.myAppState = _appState;
    }
    //Need these for triangles and ellipses
    public ArrayList<OneShape> registeredShapes;
    public ArrayList<OneShape> unregisteredShapes;
    public ArrayList<Ellipse> registeredEllipse;
    public ArrayList<Ellipse> unregisteredEllipse;
    public ShapeType myShape;
    public ShapeTypeMap myShapeMap;
    public ApplicationState myAppState;

    PaintCanvasBase paintCanvasBase;

    public void myUndo(){
        if(this.registeredShapes.size() == 0){
            System.out.println("Registered Shapes is empty");
        }
        else{
            //System.out.println("Before " + this.registeredRect.size());
            OneShape removedShape = this.registeredShapes.remove(this.registeredShapes.size() - 1);
            this.unregisteredShapes.add(removedShape);
            System.out.println("Done" + this.unregisteredShapes.size());
            //System.out.println("After " + this.registeredRect.size());
        }

        paintCanvasBase.drawAllRectangles();


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

    public void myRedo(){
        //System.out.println("Size of unregisteredRect is: " + this.unregisteredRect.size());
        /*if(this.unregisteredRect.size() > 0){
            Rectangle reintroduedRectangle = this.unregisteredRect.remove(this.unregisteredRect.size() - 1);
            this.registeredRect.add(reintroduedRectangle);
            reintroduedRectangle.draw(paintCanvasBase.getGraphics2D());
        }
        System.out.println("Size of unregisteredRect is: " + this.unregisteredRect.size());*/
        //Rectangle reintroduedRectangle = this.unregisteredRect.remove(this.unregisteredRect.size() - 1);
        //this.registeredRect.add(reintroduedRectangle);

        if(this.unregisteredShapes.size() == 0){
            System.out.println("Unregistered Shapes is empty");
        }
        else{
            OneShape reinsertedShape = this.unregisteredShapes.remove(this.unregisteredShapes.size() - 1);
            this.registeredShapes.add(reinsertedShape);
            System.out.println("Exiled rectangles " + this.unregisteredShapes.size());
            System.out.println("Redeemed rectangles " + this.registeredShapes.size());
        }

        paintCanvasBase.drawAllRectangles();
    }

    /*public void addShape(){

    }

    public void deleteShape(){

    }*/
}
