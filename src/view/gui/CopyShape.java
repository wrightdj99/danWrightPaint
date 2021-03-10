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
    private GroupedShape CopyGroup;
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
                if(MyOneShape instanceof GroupedShape){
                    System.out.println("This is a grouped shape");

                }
            }
        }
    }

    public void MyShapePaste(){
        ArrayList<OneShape> pastedShapes = new ArrayList<OneShape>();
        //Loop first to clone the GROUPED shapes
        for(OneShape MyOneShape : MyCopyUndoRedo.registeredShapes) {
            if(MyOneShape.IsCopied == true && MyOneShape instanceof GroupedShape) {
                MyOneShape.isSelected = false;
                MyOneShape.IsCopied = false;

                GroupedShape newGroupedShape = (GroupedShape) MyOneShape.CloneMe();
                newGroupedShape.isSelected = true;
                newGroupedShape.IsCopied = true;
                pastedShapes.add(newGroupedShape);
                for(OneShape newShape : newGroupedShape.MyShapes){
                    pastedShapes.add(newShape);
                }
            }
        }
        //Loop to clone the shapes if they're selected
        for(OneShape MyOneShape : MyCopyUndoRedo.registeredShapes){
            if(MyOneShape.IsCopied == true){
                if (MyOneShape instanceof  GroupedShape){
                    //do nothing. we're ignoring groupedShapes here.
                }else{
                    MyOneShape.isSelected = false;
                    MyOneShape.IsCopied = false;
                    OneShape newShape = MyOneShape.CloneMe();
                    newShape.isSelected = true;
                    newShape.IsCopied = true;
                    pastedShapes.add(newShape);
                }
            }
        }
        //Loop to add our shapes to registeredshapes
        for(OneShape MyOneShape : pastedShapes){
            MyCopyUndoRedo.registeredShapes.add(MyOneShape);
        }
        paintCanvasBase.clearItAll();
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
        for(OneShape MyOneShape : DeletedShapes){
            MyCopyUndoRedo.registeredShapes.remove(MyOneShape);
            MyCopyUndoRedo.unregisteredShapes.add(MyOneShape);
        }
        paintCanvasBase.drawAllShapes();
    }
}
