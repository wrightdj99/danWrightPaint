package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.OneShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class MyGroup implements IShape {
    public MyGroup(PaintCanvasBase _pCV){
        this.paintCanvasBase = _pCV;
        this.RegisteredGroups = new ArrayList<OneShape>();
        this.UnregisteredGroups = new ArrayList<OneShape>();
        this.myUr = _pCV.myUndoRedo;
    }
    public paintPoint HighestStartPoint, HighestEndPoint, LowestStartPoint, LowestEndPoint;
    public ApplicationState MyAppState;
    private PaintCanvasBase paintCanvasBase;
    private OneShape GroupedShape;
    public ArrayList<OneShape> RegisteredGroups;
    public ArrayList<OneShape> UnregisteredGroups;
    public undoRedo myUr;

    /*int highestEndPoint = 0;
    int lowestEndPoint = 0;
    int highestStartPoint = 0;
    int lowestStartPoint = 0;*/

    public void getHighestEndPoint(){
        for(OneShape MyShape : myUr.registeredShapes){
            //if(MyShape.endPoint.x > highestEndPoint.x || MyShape.endPoint.y > highestEndPoint)
        }
    }

    public void getLowestEndPoint(){

    }

    public void getHighestStartPoint(){

    }

    public void getLowestStartPoint(){

    }

    public void setMyWidth(){

    }

    public void setMyHeight(){

    }

    public void draw(Graphics2D graphics2D){

    }
}
