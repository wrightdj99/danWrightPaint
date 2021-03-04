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
    private OneShape MyOneShape;
    private GroupedShape MyGroupedShape;
    public ArrayList<OneShape> RegisteredGroups;
    public ArrayList<OneShape> UnregisteredGroups;
    public undoRedo myUr;
    int highestStartPointx = 0;
    int highestStartPointy = 0;
    int lowestEndPointx = Integer.MAX_VALUE;
    int lowestEndPointy = Integer.MAX_VALUE;
    int MyGroupWidth = 0;
    int MyGroupHeight = 0;
    /*int lowestEndPoint = 0;
    int highestStartPoint = 0;
    int lowestStartPoint = 0;*/

    public void GroupMe(){
        for(OneShape MyShape : myUr.registeredShapes){
            if(MyShape.isSelected){
                RegisteredGroups.add(MyShape);
            }
        }
        for(OneShape MyShape : RegisteredGroups){
            //Highest start point
            if(MyShape.startPoint.x > highestStartPointx){
                highestStartPointx = MyShape.startPoint.x;
            }
            if(MyShape.startPoint.y > highestStartPointy){
                highestStartPointy = MyShape.startPoint.y;
            }
            //Lowest end point
            if(MyShape.endPoint.x < lowestEndPointx){
                lowestEndPointx = MyShape.endPoint.x;
            }
            if(MyShape.endPoint.y < lowestEndPointy){
                lowestEndPointy = MyShape.endPoint.y;
            }
        }
        System.out.println("This is our lowest start point: " + lowestEndPointx + ", " + lowestEndPointy);
        System.out.println("This is our greatest end point: " + highestStartPointx + ", " + highestStartPointy);
        GroupedShape MyOneGroup = new GroupedShape(MyAppState, highestStartPointx, highestStartPointy);
        MyOneGroup.setMyHeight();
        MyOneGroup.setMyWidth();
        MyOneGroup.draw(paintCanvasBase.getGraphics2D());
        System.out.println("This is our width: " + MyOneGroup.width);
        System.out.println("This is our height: " + MyOneGroup.height);

    }

    public void MyUngroup(){

    }

    public void setMyWidth(){
        MyGroupWidth = highestStartPointx - lowestEndPointx;
    }

    public void setMyHeight(){
        MyGroupHeight = highestStartPointy - lowestEndPointy;
    }
}
