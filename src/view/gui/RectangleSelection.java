package view.gui;

import model.persistence.ApplicationState;
import view.interfaces.IShape;
import view.interfaces.OneShape;

import java.awt.*;

public class RectangleSelection implements IShape {
    public OneShape SelectedShape;
    public paintPoint startPoint, endPoint;
    int width, height;
    public ApplicationState myAppState;
    RectangleSelection(ApplicationState _appState, paintPoint _startPoint){
        this.myAppState = _appState;
        this.startPoint = _startPoint;
    }
    public void draw(Graphics2D graphics2D){
            Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
            graphics2D.setStroke(stroke);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawRect(this.startPoint.x - 15, this.startPoint.y - 15, this.width + 30, this.height + 30);
            graphics2D.setColor(Color.blue);
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
