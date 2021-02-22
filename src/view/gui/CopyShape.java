package view.gui;

import model.persistence.ApplicationState;
import view.gui.paintPoint;
import view.gui.undoRedo;
import view.interfaces.OneShape;
import view.interfaces.PaintCanvasBase;

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
                MyOneShape.isSelected = false;
                MyOneShape.IsCopied = false;
                OneShape newShape = MyOneShape.CloneMe();
                newShape.isSelected = true;
                newShape.IsCopied = true;
                /*newShape.startPoint.x = newShape.startPoint.x + 10;
                newShape.startPoint.y = newShape.startPoint.y - 10;
                newShape.endPoint.x = newShape.endPoint.x + 10;
                newShape.endPoint.y = newShape.endPoint.y - 10;*/
                pastedShapes.add(newShape);
            }
        }
        //Loop to add our shapes to registeredshapes
        for(OneShape MyOneShape : pastedShapes){
            MyCopyUndoRedo.registeredShapes.add(MyOneShape);
        }
        paintCanvasBase.drawAllShapes();
    }

    public void DeleteMe(){
        ArrayList<OneShape> DeletedShapes = new ArrayList<OneShape>();
        for(OneShape MyOneShape : MyCopyUndoRedo.registeredShapes){
            if(MyOneShape.isSelected == true){
                //MyOneShape.isSelected = false;
                MyOneShape.IsCopied = false;
                DeletedShapes.add(MyOneShape);
                //MyOneShape.isSelected = false;
            }
        }

        //MyCopyUndoRedo.registeredShapes.remove(MyOneShape);

        for(OneShape MyOneShape : DeletedShapes){
            MyCopyUndoRedo.registeredShapes.remove(MyOneShape);
            MyCopyUndoRedo.unregisteredShapes.add(MyOneShape);
        }

        paintCanvasBase.drawAllShapes();
    }
}
