package view.interfaces;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingMap;
import model.ShapeShadingType;
import model.persistence.ApplicationState;

import java.awt.*;

public class Triangle extends OneShape implements myShape{
    public ApplicationState myAppState;
    public ShapeColorMap myShapeColorMap;
    public ShapeShadingMap myShade;
    //public boolean isSelected;
    ShapeColor myColor;
    ShapeColor mySecondaryColor;
    ShapeShadingType myActiveShade;

    Triangle(ApplicationState _appState, paintPoint _startPoint){
        this.myAppState = _appState;
        this.isSelected = false;
        this.myColor = myAppState.getActivePrimaryColor();
        this.mySecondaryColor = myAppState.getActiveSecondaryColor();
        this.myShade = new ShapeShadingMap();
        this.myActiveShade = myAppState.getActiveShapeShadingType();
        this.myShapeColorMap = new ShapeColorMap();
        this.startPoint = _startPoint;
        this.isSelected = false;

    }

    public void draw(Graphics2D graphics2D){
        System.out.println("We drew a Triangle!");
        this.setMyHeight();
        this.setMyWidth();
        if(this.myActiveShade.equals(this.myActiveShade.OUTLINE)) {
            if(this.isSelected == true){
                graphics2D.setColor(Color.PINK);
            }
            else{
                graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            /*int[] xPoints = {this.startPoint.x - 5, this.endPoint.x - 5, this.startPoint.x - 5};
            int[] yPoints = {this.startPoint.y - 5, this.endPoint.y - 5, this.endPoint.y - 5};
            graphics2D.fillPolygon(xPoints, yPoints, 3);*/
                int funnyX = (int)Math.round((float)this.width / (float)this.height * 10D);
                int funnyY = (int)Math.round((float)this.height / (float)this.width * 10D);
                int[] xPoints = {this.startPoint.x - 5, this.endPoint.x + funnyX, this.startPoint.x - 5};
                int[] yPoints = {this.startPoint.y - funnyY, this.endPoint.y + 5, this.endPoint.y + 5};
                graphics2D.fillPolygon(xPoints, yPoints, 3);
                graphics2D.setColor(Color.WHITE);
                //graphics2D.fillRect(this.startPoint.x - 5, this.startPoint.y - 5, this.width + 10, this.height + 10);
                //graphics2D.setColor(Color.white);
            }
        }

        else if(this.myActiveShade.equals(myActiveShade.OUTLINE_AND_FILLED_IN)){
            if(this.isSelected == true){
                graphics2D.setColor(Color.PINK);
            }
            else{
                graphics2D.setColor(myShapeColorMap.getMySecondaryShapeColor(this.mySecondaryColor));
                int funnyX = (int)Math.round((float)this.width / (float)this.height * 10D);
                int funnyY = (int)Math.round((float)this.height / (float)this.width * 10D);
                int[] xPoints = {this.startPoint.x - 5, this.endPoint.x + funnyX, this.startPoint.x - 5};
                int[] yPoints = {this.startPoint.y - funnyY, this.endPoint.y + 5, this.endPoint.y + 5};
                graphics2D.fillPolygon(xPoints, yPoints, 3);
                graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }

        else{
            if(this.isSelected == true){
                graphics2D.setColor(Color.PINK);
            }
            else{
                graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }

        Polygon triangle = new Polygon();
        triangle.addPoint(startPoint.x, startPoint.y);
        triangle.addPoint(endPoint.x, endPoint.y);
        triangle.addPoint(startPoint.x, endPoint.y);
        graphics2D.fillPolygon(triangle);
        /*int[] xPoints = {this.startPoint.x, this.endPoint.x, this.startPoint.x};
        int[] yPoints = {this.startPoint.x, this.endPoint.y, this.endPoint.y};
        graphics2D.fillPolygon(xPoints, yPoints, 3);*/
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
}
