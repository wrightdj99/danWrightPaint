package view.interfaces;

import java.awt.*;

public class Rectangle implements myShape{

    public paintPoint startPoint, endPoint;
    int width, height;
    Rectangle(paintPoint _startPoint){
        this.startPoint = _startPoint;
    }

    public void draw(Graphics2D graphics2d){
        this.width = this.getMyWidth();
        this.height = this.getMyHeight();
        System.out.println("We drew a shape");
        graphics2d.setColor(Color.green);
        graphics2d.fillRect(this.startPoint.x, this.startPoint.y, this.width, this.height);
    }

    public int getMyWidth(){
        int endWidth = this.endPoint.x - this.startPoint.x;
        if (endWidth < 0){
            endWidth = -1 * endWidth;
            this.startPoint.x = this.startPoint.x - endWidth;
        }
        return endWidth;
    }

    public int getMyHeight(){
        int endHeight = this.endPoint.y - this.startPoint.y;
        if (endHeight < 0){
            endHeight = -1 * endHeight;
            this.startPoint.y = this.startPoint.y - endHeight;
        }
        return endHeight;
    }

    //methods for get start and end points here
}
