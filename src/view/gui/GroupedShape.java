package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.OneShape;

import java.awt.*;

public class GroupedShape implements IShape {
    //public paintPoint startPoint, endPoint;
    int startPointx, startPointy;
    int endPointx, endPointy;
    int width, height;
    public ApplicationState applicationState;
    public MyGroup myGroup;
    public GroupedShape(ApplicationState _myApp, int startPointx, int startPointy){
        this.applicationState = _myApp;
        //this.startPoint = _startPoint;
        this.startPointx = startPointx;
        this.startPointy = startPointy;
        this.myGroup = myGroup;
    }

    public void setMyWidth() {
        this.width = this.endPointx - this.startPointx;
        if(this.width < 0){
            this.width = this.width * -1;
        }
        else{

        }

    }

    public void setMyHeight() {
        this.height = this.endPointy - this.startPointy;
        if(this.height < 0){
            this.height = this.height * -1;
        }
        else{

        }
    }

    public void draw(Graphics2D graphics2D){
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(this.startPointx - 15, this.startPointy - 15, this.width + 30, this.height + 30);
    }
}
