package view.gui;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingMap;
import model.ShapeShadingType;
import model.persistence.ApplicationState;
import view.interfaces.OneShape;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class Triangle extends OneShape implements IShape {
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

    public void draw(Graphics2D graphics2D, ArrayList<OneShape> registeredShapes){
        this.setMyHeight();
        this.setMyWidth();
        int funnyX = (int)Math.round((float)this.width / (float)this.height * 10D);
        int funnyY = (int)Math.round((float)this.height / (float)this.width * 10D);
        boolean _amIPartOfAGroup = this.CheckIfIAmPartOfAGroup(registeredShapes);
        if(this.myActiveShade.equals(this.myActiveShade.OUTLINE)) {
            if(this.isSelected == true){
                SelectionTriangleDecorator selectionTriangleDecorator = new SelectionTriangleDecorator(this, _amIPartOfAGroup);
                selectionTriangleDecorator.draw(graphics2D, registeredShapes);
            }
            else{
                graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
                int[] xPoints = {this.startPoint.x - 5, this.endPoint.x + funnyX, this.startPoint.x - 5};
                int[] yPoints = {this.startPoint.y - funnyY, this.endPoint.y + 5, this.endPoint.y + 5};
                graphics2D.fillPolygon(xPoints, yPoints, 3);
                graphics2D.setColor(Color.WHITE);
            }
        }

        else if(this.myActiveShade.equals(myActiveShade.OUTLINE_AND_FILLED_IN)){
            if(this.isSelected == true){
                SelectionTriangleDecorator selectionTriangleDecorator = new SelectionTriangleDecorator(this, _amIPartOfAGroup);
                selectionTriangleDecorator.draw(graphics2D, registeredShapes);
            }
            else{
                graphics2D.setColor(myShapeColorMap.getMySecondaryShapeColor(this.mySecondaryColor));
                funnyX = (int)Math.round((float)this.width / (float)this.height * 10D);
                funnyY = (int)Math.round((float)this.height / (float)this.width * 10D);
                int[] xPoints = {this.startPoint.x - 5, this.endPoint.x + funnyX, this.startPoint.x - 5};
                int[] yPoints = {this.startPoint.y - funnyY, this.endPoint.y + 5, this.endPoint.y + 5};
                graphics2D.fillPolygon(xPoints, yPoints, 3);
                graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }

        else{
            if(this.isSelected == true){
                SelectionTriangleDecorator selectionTriangleDecorator = new SelectionTriangleDecorator(this, _amIPartOfAGroup);
                selectionTriangleDecorator.draw(graphics2D, registeredShapes);
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
            this.endPoint.x = this.startPoint.x - endWidth;
            //this.startPoint.x = this.startPoint.x - endWidth;
            //this.endPoint.x = this.endPoint.x - endWidth;
            //this.startPoint.x = this.startPoint.x - endWidth;
        }
        this.width = endWidth;
    }

    public void setMyHeight(){
        int endHeight = this.endPoint.y - this.startPoint.y;
        if (endHeight < 0){
            endHeight = -1 * endHeight;
            this.endPoint.y = this.startPoint.y - endHeight;
            //this.startPoint.y = this.startPoint.y - endHeight;
            //this.endPoint.y = this.endPoint.y - endHeight;
        }
        this.height = endHeight;
    }

    public OneShape CloneMe(){
        //if(this.IsCopied == true){
            paintPoint newStartPoint = new paintPoint();
            newStartPoint.x = this.startPoint.x + 30;
            newStartPoint.y = this.startPoint.y + 30;
            paintPoint newEndPoint = new paintPoint();
            newEndPoint.x = this.endPoint.x + 30;
            newEndPoint.y = this.endPoint.y + 30;
            OneShape newShape = TriangleFactory.createTriangle(myAppState, newStartPoint);
            newShape.endPoint = newEndPoint;
            this.setMyHeight();
            this.setMyWidth();

            return newShape;
        //}
    }
}
