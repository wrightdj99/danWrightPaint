package view.interfaces;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingMap;
import model.ShapeShadingType;
import model.persistence.ApplicationState;

import java.awt.*;

public class Ellipse extends OneShape implements myShape{
    public ApplicationState myAppState;
    public ShapeColorMap myShapeColorMap;
    public ShapeShadingMap myShade;
    ShapeColor myColor;
    ShapeColor mySecondaryColor;
    ShapeShadingType myActiveShade;
    Ellipse(ApplicationState _myAppState, paintPoint _startPoint){
        this.myAppState = _myAppState;
        this.startPoint = _startPoint;
        this.myColor = myAppState.getActivePrimaryColor();
        this.mySecondaryColor = myAppState.getActiveSecondaryColor();
        this.myShade = new ShapeShadingMap();
        this.myActiveShade = myAppState.getActiveShapeShadingType();
        this.myShapeColorMap = new ShapeColorMap();
    }

    public void draw(Graphics2D graphics2D){
        System.out.println("We drew an Ellipse!");
        if(this.myActiveShade.equals(this.myActiveShade.OUTLINE)){
            graphics2D.setColor(this.myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            graphics2D.fillOval(this.startPoint.x - 10, this.startPoint.y - 10, this.width + 10, this.height + 10);
            graphics2D.setColor(Color.WHITE);
        }

        else if(this.myActiveShade.equals(this.myActiveShade.OUTLINE_AND_FILLED_IN)){
            graphics2D.setColor(this.myShapeColorMap.getMySecondaryShapeColor(this.mySecondaryColor));
            graphics2D.fillOval(this.startPoint.x - 10, this.startPoint.y - 10, this.width + 10, this.height + 10);
            graphics2D.setColor(this.myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
        }

        else{
            graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
        }

        graphics2D.fillOval(this.startPoint.x - 5, this.startPoint.y - 5, this.width, this.height);
    }

    public void setMyWidth(){
        int endWidth = this.endPoint.x - this.startPoint.x;
        if(endWidth < 0){
            endWidth = -1 * endWidth;
            this.startPoint.x = this.startPoint.x - endWidth;
        }

        this.width = endWidth;
    }

    public void setMyHeight(){
        int endHeight = this.endPoint.y - this.startPoint.y;
        if(endHeight < 0){
            endHeight = -1 * endHeight;
            this.startPoint.y = this.startPoint.y - endHeight;
        }

        this.height = endHeight;
    }


}
