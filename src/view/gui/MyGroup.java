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
        this.MyOneGroup.isSelected = true;
        this.highestStartPointx = Integer.MAX_VALUE;
        this.highestStartPointy = Integer.MAX_VALUE;
        this.lowestEndPointx = 0;
        this.lowestEndPointy = 0;
        for(OneShape MyShape : myUr.registeredShapes){
            if(MyShape.isSelected){
                if(MyOneGroup.MyShapes.contains(MyOneShape)){
                    MyOneGroup.MyShapes.remove(MyOneShape);
                }
                else{
                    this.MyOneGroup.MyShapes.add(MyShape);
                }
            }
        }
        /*if(MyOneGroup.MyShapes.contains(this.MyOneShape)){
            MyOneGroup.MyShapes.remove(this.MyOneShape);
        }*/

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
        System.out.println("Registered Group Shapes: " + this.MyOneGroup.MyShapes.size());
        myUr.registeredShapes.add(this.MyOneGroup);
        //MyOneGroup.draw(paintCanvasBase.getGraphics2D());
        paintCanvasBase.drawAllShapes();

    }

    public void MyUngroup(){

        if(this.MyOneGroup.MyShapes.size() == 0 && this.MyOneGroup.MyShapes.size() == 0){
            System.out.println("No more groups");
        }
        else{
            /*for(OneShape MyShape : this.MyOneGroup.MyShapes){
                if(MyShape.isSelected){
                    MyShape.isSelected = false;
                    //this.MyOneGroup.MyShapes.remove(MyShape);
                }
            }*/
            //OneShape UngroupedShape = this.MyOneGroup.MyShapes.remove(this.MyOneGroup.MyShapes.size() - 1);
            //myUr.registeredShapes.remove(UngroupedShape);
            //this.MyOneGroup.MyShapes.remove(UngroupedShape);
            /*for(MyOneShapeDel : myUr.registeredShapes){
                if(MyOneShapeDel instanceof GroupedShape){
                    //myUr.registeredShapes.remove(MyOneShape);
                    MyOneShapeDel.isSelected = false;
                }
                //myUr.registeredShapes.remove(MyOneShapeDel);
            }*/
            /*for(OneShape MyOneShapeDel : myUr.registeredShapes){
                if(MyOneShapeDel instanceof GroupedShape){
                    myUr.registeredShapes.remove(MyOneShapeDel);
                    myUr.unregisteredShapes.add(MyOneShapeDel);
                }
            }*/
            /*OneShape UngroupedShape = this.MyOneGroup.MyShapes.remove(this.MyOneGroup.MyShapes.size() - 1);

            if(UngroupedShape instanceof GroupedShape){
                //this.MyOneGroup.MyShapes.remove(UngroupedShape);
                this.MyOneGroup.MyShapes.clear();
            }*/
            GroupedShape shapeToRemove = null;
            for(OneShape shape : this.myUr.registeredShapes){
                if (shape instanceof  GroupedShape){
                    shapeToRemove = (GroupedShape) shape;
                }
            }
            if (shapeToRemove != null){
                for(OneShape shape : this.MyOneGroup.MyShapes){
                    shape.isSelected = false;
                }
                this.myUr.registeredShapes.remove(shapeToRemove);
            }
            /*
            this.MyOneGroup = (GroupedShape) MyOneGroup.MyShapes.remove(this.MyOneGroup.MyShapes.size() - 1);
            this.MyOneGroup.MyShapes.clear();
            if(MyOneGroup instanceof GroupedShape){
                this.MyOneGroup.isSelected = false;
                myUr.unregisteredShapes.remove(this.MyOneGroup);
            }
            */
            System.out.println(this.MyOneGroup.MyShapes.size());
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
