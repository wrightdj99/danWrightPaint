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
    int highestStartPointx = Integer.MAX_VALUE;
    int highestStartPointy = Integer.MAX_VALUE;
    int lowestEndPointx = 0;
    int lowestEndPointy = 0;
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
        System.out.println("Total items in RegisteredGroups is: " + RegisteredGroups.size());
        for(OneShape MyShape : RegisteredGroups){
            //Highest start point
            if(MyShape.startPoint.x < highestStartPointx){
                highestStartPointx = MyShape.startPoint.x;
            }
            if(MyShape.startPoint.y < highestStartPointy){
                highestStartPointy = MyShape.startPoint.y;
            }
            //Lowest end point
            if(MyShape.endPoint.x > lowestEndPointx){
                lowestEndPointx = MyShape.endPoint.x;
            }
            if(MyShape.endPoint.y > lowestEndPointy){
                lowestEndPointy = MyShape.endPoint.y;
            }
        }
        System.out.println("This is our lowest start point: " + lowestEndPointx + ", " + lowestEndPointy);
        System.out.println("This is our greatest end point: " + highestStartPointx + ", " + highestStartPointy);
        paintPoint groupStartPoint = new paintPoint();
        groupStartPoint.y = highestStartPointy;
        groupStartPoint.x = highestStartPointx;
        paintPoint groupEndPoint = new paintPoint();
        groupEndPoint.y = lowestEndPointy;
        groupEndPoint.x = lowestEndPointx;
        GroupedShape MyOneGroup = new GroupedShape(MyAppState, groupStartPoint, groupEndPoint);

        MyOneGroup.setMyHeight();
        MyOneGroup.setMyWidth();
        MyOneGroup.draw(paintCanvasBase.getGraphics2D());

        //System.out.println("This is our width: " + myRepresentativeWidth);
        //System.out.println("This is our height: " + myRepresentativeHeight);

    }

    public void MyUngroup(){

    }

    public void setMyWidth(){
        //MyGroupWidth = highestStartPointx - lowestEndPointx;
    }

    public void setMyHeight(){
        //MyGroupHeight = highestStartPointy - lowestEndPointy;
    }
}
