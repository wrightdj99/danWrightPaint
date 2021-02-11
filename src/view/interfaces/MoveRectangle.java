package view.interfaces;

import model.persistence.ApplicationState;

public class MoveRectangle implements myShape {
    paintPoint startPoint;
    paintPoint endPoint;
    int height, width;
    public ApplicationState MyAppState;
    public MoveRectangle(ApplicationState _MyApp, paintPoint _StartPoint){
        this.MyAppState = _MyApp;
        this.startPoint = _StartPoint;
    }
    public void setMyHeight(){
        this.height = this.endPoint.y - this.startPoint.y;
    }

    public void setMyWidth(){
        this.width = this.endPoint.x - this.startPoint.x;
    }

    public void getDifference(){
        System.out.println("Change in x: " + this.width);
        System.out.println("Change in y: " + this.height);
    }
}
