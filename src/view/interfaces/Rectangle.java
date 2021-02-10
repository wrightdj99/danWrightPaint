package view.interfaces;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingMap;
import model.ShapeShadingType;
import model.persistence.ApplicationState;

import java.awt.*;

public class Rectangle extends OneShape implements myShape{

    public ApplicationState myAppState;
    public ShapeColorMap myShapeColorMap;
    public ShapeShadingMap myShade;
    ShapeColor myColor;
    ShapeColor mySecondaryColor;
    ShapeShadingType myActiveShade;
    Rectangle(ApplicationState _appState, paintPoint _startPoint){
        this.myAppState = _appState;
        this.myColor = myAppState.getActivePrimaryColor();
        this.mySecondaryColor = myAppState.getActiveSecondaryColor();
        this.myShade = new ShapeShadingMap();
        this.myActiveShade = myAppState.getActiveShapeShadingType();
        this.myShapeColorMap = new ShapeColorMap();
        this.startPoint = _startPoint;
        this.isSelected = false;
    }

    /*public Rectangle() {

    }*/
    //@Override
    public void draw(Graphics2D graphics2d){
        //this.width = this.getMyWidth();
        //this.height = this.getMyHeight();
        System.out.println("We drew a Rectangle");
        if(this.myActiveShade.equals(this.myActiveShade.OUTLINE)) {
            //SUPPOSED TO SET THIS TO SECONDARY COLOR
            if(this.isSelected == true){
                Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                graphics2d.setStroke(stroke);
                graphics2d.setColor(Color.BLACK);
                graphics2d.drawRect(this.startPoint.x - 15, this.startPoint.y - 15, this.width + 30, this.height + 30);
                graphics2d.setColor(Color.blue);
            }
            else{
                graphics2d.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
                graphics2d.fillRect(this.startPoint.x - 5, this.startPoint.y - 5, this.width + 10, this.height + 10);
                graphics2d.setColor(Color.WHITE);
            }
        }

        else if(this.myActiveShade.equals(myActiveShade.OUTLINE_AND_FILLED_IN)){
            if(this.isSelected == true){
                graphics2d.setColor(Color.PINK);
            }
            else{
                graphics2d.setColor(myShapeColorMap.getMySecondaryShapeColor(this.mySecondaryColor));
                graphics2d.fillRect(this.startPoint.x - 5, this.startPoint.y - 5, this.width + 10, this.height + 10);
                graphics2d.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }

        else{
            if(this.isSelected == true){
                graphics2d.setColor(Color.PINK);
            }
            else{
                graphics2d.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }
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
