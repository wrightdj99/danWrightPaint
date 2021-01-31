package view.interfaces;

import model.ShapeColor;
import model.ShapeColorMap;
import model.persistence.ApplicationState;

import java.awt.*;

public class Rectangle implements myShape{

    public paintPoint startPoint, endPoint;
    int width, height;
    public ApplicationState myAppState;
    public ShapeColorMap myShapeColorMap;
    ShapeColor myColor;
    Rectangle(ApplicationState _appState, paintPoint _startPoint){
        this.myAppState = _appState;
        this.myColor = myAppState.getActivePrimaryColor();
        this.myShapeColorMap = new ShapeColorMap();
        this.startPoint = _startPoint;
    }

    /*public Rectangle() {

    }*/

    public void draw(Graphics2D graphics2d){
        //this.width = this.getMyWidth();
        //this.height = this.getMyHeight();
        System.out.println("We drew a shape");
        graphics2d.setColor(myShapeColorMap.getMyShapeColor(this.myColor));
        graphics2d.fillRect(this.startPoint.x, this.startPoint.y, this.width, this.height);
    }

    public void setMyWidth(){
        int endWidth = this.endPoint.x - this.startPoint.x;
        if (endWidth < 0){
            endWidth = -1 * endWidth;
            this.startPoint.x = this.startPoint.x - endWidth;
        }
        this.width = endWidth;
    }

    public void setMyHeight(){
        int endHeight = this.endPoint.y - this.startPoint.y;
        if (endHeight < 0){
            endHeight = -1 * endHeight;
            this.startPoint.y = this.startPoint.y - endHeight;
        }
        this.height = endHeight;
    }
    /*
    public void setMyColor(Color aColor){
        this.myColor = myAppState.getActivePrimaryColor();
    }
     */


    //methods for get start and end points here
}
