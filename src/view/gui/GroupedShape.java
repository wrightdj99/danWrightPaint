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
    int width, height;
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
    @Override
    public OneShape CloneMe(){
        return null;
    }
    public void draw(Graphics2D graphics2D){
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.RED);
        if (endHeight < 0 || endWidth < 0) {
            graphics2D.drawRect(this.endPoint.x + 15, this.endPoint.y + 15, this.width - 30, this.height - 30);
        }
        else{
            graphics2D.drawRect(this.startPoint.x - 15, this.startPoint.y - 15, this.width + 30, this.height + 30);
        }
    }
}
