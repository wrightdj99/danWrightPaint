package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.OneShape;

import java.awt.*;
import java.util.ArrayList;

public class GroupedShape extends OneShape implements IShape {
    //public paintPoint startPoint, endPoint;
    //int startPointx, startPointy;
    //int endPointx, endPointy;
    //int width, height;
    int endWidth, endHeight;
    public ApplicationState applicationState;
    public MyGroup myGroup;
    public ArrayList<OneShape> MyShapes;


    public GroupedShape(ApplicationState _myApp, paintPoint _startPoint, paintPoint _endPoint){
        this.applicationState = _myApp;
        this.startPoint = _startPoint;
        this.endPoint = _endPoint;
        this.MyShapes = new ArrayList<OneShape>();
    }

    public void setMyWidth() {
        endWidth = this.startPoint.x - this.endPoint.x;
        if(endWidth < 0){
            endWidth = endWidth * -1;
        }
        this.width = endWidth;


    }

    public void setMyHeight() {
        endHeight = this.startPoint.y - this.endPoint.y;
        if(endHeight < 0){
            endHeight = endHeight * -1;
        }
        this.height = endHeight;
    }

    public GroupedShape CloneMe(){
        paintPoint newStartPoint = new paintPoint();
        newStartPoint.x = this.startPoint.x + 30;
        newStartPoint.y = this.startPoint.y + 30;
        paintPoint newEndPoint = new paintPoint();
        newEndPoint.x = this.endPoint.x + 30;
        newEndPoint.y = this.endPoint.y + 30;
        GroupedShape newGroupedShape = new GroupedShape(this.applicationState, newStartPoint, newEndPoint);
        System.out.println(newGroupedShape.MyShapes.size());
        //newShape.startPoint = newStartPoint;
        //newShape.endPoint = newEndPoint;
        newGroupedShape.setMyHeight();
        newGroupedShape.setMyWidth();
        ArrayList<OneShape> newMyShapes = new ArrayList<OneShape>();
        for(OneShape theShape : this.MyShapes){
            theShape.isSelected = false;
            theShape.IsCopied = false;
            OneShape newShape = theShape.CloneMe();
            newShape.isSelected = true;
            newShape.IsCopied = true;
            newMyShapes.add(newShape);
        }
        newGroupedShape.MyShapes = newMyShapes;
        return newGroupedShape;
    }
    public void draw(Graphics2D graphics2D, ArrayList<OneShape> registeredShapes){
        //Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        //graphics2D.setStroke(stroke);
        boolean _amIPartOfAGroup = this.CheckIfIAmPartOfAGroup(registeredShapes);
        if (this.isSelected == true){
            SelectionGroupedShapeDecorator selectionGroupedShapeDecorator = new SelectionGroupedShapeDecorator(this, _amIPartOfAGroup);
            selectionGroupedShapeDecorator.draw(graphics2D, registeredShapes);
        }else{
            Stroke stroke = new BasicStroke(1);
            graphics2D.setStroke(stroke);
            graphics2D.setColor(Color.red);
            if (endHeight < 0 || endWidth < 0) {
                graphics2D.drawRect(this.endPoint.x + 15, this.endPoint.y + 15, this.width - 30, this.height - 30);

            }
            else{
                graphics2D.drawRect(this.startPoint.x - 15, this.startPoint.y - 15, this.width + 30, this.height + 30);
            }
        }
    }
}
