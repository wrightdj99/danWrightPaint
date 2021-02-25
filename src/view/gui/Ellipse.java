package view.gui;

import model.ShapeColor;
import model.ShapeColorMap;
import model.ShapeShadingMap;
import model.ShapeShadingType;
import model.persistence.ApplicationState;
import view.interfaces.OneShape;
import view.interfaces.IShape;

import java.awt.*;

public class Ellipse extends OneShape implements IShape {
    public ApplicationState myAppState;
    public ShapeColorMap myShapeColorMap;
    public ShapeShadingMap myShade;
    private RectangleSelection EllipseSelected;
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
        this.isSelected = false;
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

    public void draw(Graphics2D graphics2D){
        //System.out.println("We drew an Ellipse!");
        if(this.myActiveShade.equals(this.myActiveShade.OUTLINE)){
            if(this.isSelected == true){
                SelectionEllipseDecorator selectionEllipseDecorator = new SelectionEllipseDecorator(this);
                selectionEllipseDecorator.draw(graphics2D);
            }
            else{
                graphics2D.setColor(this.myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
                graphics2D.fillOval(this.startPoint.x - 10, this.startPoint.y - 10, this.width + 20, this.height + 20);
                graphics2D.setColor(Color.WHITE);
            }
        }

        else if(this.myActiveShade.equals(this.myActiveShade.OUTLINE_AND_FILLED_IN)){
            if(this.isSelected == true){
                SelectionEllipseDecorator selectionEllipseDecorator = new SelectionEllipseDecorator(this);
                selectionEllipseDecorator.draw(graphics2D);
            }
            else{
                graphics2D.setColor(this.myShapeColorMap.getMySecondaryShapeColor(this.mySecondaryColor));
                graphics2D.fillOval(this.startPoint.x - 10, this.startPoint.y - 10, this.width + 20, this.height + 20);
                graphics2D.setColor(this.myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }

        else{
            if(this.isSelected == true){
                SelectionEllipseDecorator selectionEllipseDecorator = new SelectionEllipseDecorator(this);
                selectionEllipseDecorator.draw(graphics2D);
            }
            else{
                graphics2D.setColor(myShapeColorMap.getMyPrimaryShapeColor(this.myColor));
            }
        }

        graphics2D.fillOval(this.startPoint.x, this.startPoint.y, this.width, this.height);
    }

    public OneShape CloneMe(){
        paintPoint newStartPoint = new paintPoint();
        newStartPoint.x = this.startPoint.x + 30;
        newStartPoint.y = this.startPoint.y + 30;
        paintPoint newEndPoint = new paintPoint();
        newEndPoint.x = this.endPoint.x + 30;
        newEndPoint.y = this.endPoint.y + 30;
        Ellipse newShape = EllipseFactory.createEllipse(myAppState, newStartPoint);
        newShape.startPoint = newStartPoint;
        newShape.endPoint = newEndPoint;
        newShape.setMyHeight();
        newShape.setMyWidth();
        return newShape;
    }


}
