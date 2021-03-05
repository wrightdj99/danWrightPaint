package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.OneShape;

import java.awt.*;

public class GroupedShape extends OneShape implements IShape {
    //public paintPoint startPoint, endPoint;
    //int startPointx, startPointy;
    //int endPointx, endPointy;
    int width, height;
    int endWidth, endHeight;
    public ApplicationState applicationState;
    public MyGroup myGroup;
    public GroupedShape(ApplicationState _myApp, paintPoint _startPoint, paintPoint _endPoint){
        this.applicationState = _myApp;
        this.startPoint = _startPoint;
        this.endPoint = _endPoint;
    }

    public void setMyWidth() {
        endWidth = this.startPoint.x - this.endPoint.x;
        if(endWidth < 0){
            endWidth = endWidth * -1;
        }
        else{

        }
        this.width = endWidth;


    }

    public void setMyHeight() {
        endHeight = this.startPoint.y - this.endPoint.y;
        if(endHeight < 0){
            endHeight = endHeight * -1;
        }
        else{

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
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(this.startPoint.x, this.startPoint.y, this.width, this.height);
    }
}
