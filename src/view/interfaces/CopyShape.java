package view.interfaces;

import model.ShapeType;
import model.persistence.ApplicationState;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class CopyShape {
    PaintCanvasBase paintCanvasBase;
    public ApplicationState MyAppState;
    paintPoint StartPoint, EndPoint;
    private OneShape ClonedShape;
    int width, height;
    private undoRedo MyCopyUndoRedo;
    //public ShapeType MyShapeType;
    //public ArrayList<OneShape> registeredShapes;
    public CopyShape(PaintCanvasBase _pCV, ApplicationState _MyAppState){
        this.paintCanvasBase = _pCV;
        this.MyAppState = _MyAppState;
        this.MyCopyUndoRedo = paintCanvasBase.myUndoRedo;
        //this.MyShapeType = MyAppState.getActiveShapeType();
        //this.StartPoint = _StartPoint;
    }

    public void MyShapeCopy(){
        //System.out.println("Shape is Copied");
        System.out.println("Is copied");
        for(OneShape MyOneShape : MyCopyUndoRedo.registeredShapes){
            if(MyOneShape.isSelected == true){
                MyOneShape.IsCopied = true;
            }
        }
    }

    public void MyShapePaste(){
        ArrayList<OneShape> pastedShapes = new ArrayList<OneShape>();
        //Loop to clone the shapes if they're selected
        for(OneShape MyOneShape : MyCopyUndoRedo.registeredShapes){
            if(MyOneShape.IsCopied == true){
                //System.out.println("Let's Clone The Shape");
                MyOneShape.isSelected = false;
                MyOneShape.IsCopied = false;
                OneShape newShape = MyOneShape.CloneMe();
                newShape.isSelected = true;
                newShape.IsCopied = true;
                pastedShapes.add(newShape);
            }
        }
        //Loop to add our shapes to registeredshapes
        for(OneShape MyOneShape : pastedShapes){
            MyCopyUndoRedo.registeredShapes.add(MyOneShape);
        }


        System.out.println("Our pasted size: " + pastedShapes.size() + "\n");
        System.out.println("Our registered shape size: " + MyCopyUndoRedo.registeredShapes.size() + "\n");

        paintCanvasBase.drawAllShapes();
    }
}
