package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.OneShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class MyGroup implements IShape {
    public MyGroup(PaintCanvasBase _pCV){
        this.paintCanvasBase = _pCV;
        //this.RegisteredGroupShapes = new ArrayList<OneShape>();
        //this.UnregisteredGroupShapes = new ArrayList<OneShape>();
        //this.OurGroups = new ArrayList<GroupedShape>();
        //this.MyGroupedShape = MyOneGroup;
        this.myUr = _pCV.myUndoRedo;
    }
    public paintPoint HighestStartPoint, HighestEndPoint, LowestStartPoint, LowestEndPoint;
    private ApplicationState MyAppState;
    private PaintCanvasBase paintCanvasBase;
    private OneShape MyOneShape;
    //private GroupedShape MyGroupedShape;
    //public ArrayList<OneShape> RegisteredGroupShapes;
    //public ArrayList<GroupedShape> OurGroups;
    //public ArrayList<OneShape> UnregisteredGroupShapes;
    public undoRedo myUr;
    paintPoint groupEndPoint;
    private paintPoint groupStartPoint;
    private GroupedShape MyOneGroup;
    int highestStartPointx;
    int highestStartPointy;
    int lowestEndPointx;
    int lowestEndPointy;
    int MyGroupWidth = 0;
    int MyGroupHeight = 0;



    public void GroupMe(){
        groupEndPoint = new paintPoint();
        groupStartPoint = new paintPoint();
        this.MyOneGroup =  new GroupedShape(MyAppState, groupStartPoint, groupEndPoint);
        this.highestStartPointx = Integer.MAX_VALUE;
        this.highestStartPointy = Integer.MAX_VALUE;
        this.lowestEndPointx = 0;
        this.lowestEndPointy = 0;
        for(OneShape MyShape : myUr.registeredShapes){
            if(MyShape.isSelected){
                this.MyOneGroup.MyShapes.add(MyShape);
            }
        }

        System.out.println("Total items in RegisteredGroups is: " + this.MyOneGroup.MyShapes.size());
        for(OneShape MyShape : this.MyOneGroup.MyShapes){
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
        //paintPoint groupStartPoint = new paintPoint();
        groupStartPoint.y = highestStartPointy;
        groupStartPoint.x = highestStartPointx;
        //paintPoint groupEndPoint = new paintPoint();
        groupEndPoint.y = lowestEndPointy;
        groupEndPoint.x = lowestEndPointx;
        //GroupedShape MyOneGroup = new GroupedShape(MyAppState, groupStartPoint, groupEndPoint);
        MyOneGroup.setMyHeight();
        MyOneGroup.setMyWidth();
        MyOneGroup.draw(paintCanvasBase.getGraphics2D());
        //System.out.println("Before: " + RegisteredGroupShapes.size());
        System.out.println("Shapes in list we want: " + this.MyOneGroup.MyShapes.size());
        //System.out.println("B4: " + myUr.registeredShapes.size());
        //this.RegisteredGroupShapes.add(MyOneGroup);
        myUr.registeredShapes.add(this.MyOneGroup);

        //System.out.println("After: " + RegisteredGroupShapes.size());
        //System.out.println("AFARTAR: " + myUr.registeredShapes.size());


        //System.out.println("This is our width: " + myRepresentativeWidth);
        //System.out.println("This is our height: " + myRepresentativeHeight);

    }

    public void MyUngroup(){
        if(this.MyOneGroup.MyShapes.size() == 0 && this.MyOneGroup.MyShapes.size() == 0){
            System.out.println("No more groups");
        }
        else{
            /*for(OneShape MyShape : RegisteredGroupShapes){
                MyShape.isSelected = true;
                //RegisteredGroups.remove(MyShape);
                UnregisteredGroupShapes.add(MyShape);
            }
            RegisteredGroupShapes.remove(MyGroupedShape);*/
            //for(OneShape MyShape : myUr.registeredShapes){

            //}
            myUr.registeredShapes.remove(MyOneGroup);

            System.out.println("RegisteredGroups size: " + this.MyOneGroup.MyShapes.size());
            System.out.println("UnregisteredGroups size: " + this.MyOneGroup.MyShapes.size());

        }
        paintCanvasBase.drawAllShapes();
    }

    public void setMyWidth(){
        //MyGroupWidth = highestStartPointx - lowestEndPointx;
    }

    public void setMyHeight(){
        //MyGroupHeight = highestStartPointy - lowestEndPointy;
    }
}
