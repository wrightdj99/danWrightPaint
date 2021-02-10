package view.interfaces;

import model.persistence.ApplicationState;

public class SelectionRectangle implements myShape{
    public paintPoint startPoint, endPoint;
    int width, height;
    public ApplicationState myAppState;
    SelectionRectangle(ApplicationState _appState, paintPoint _startPoint){
        this.myAppState = _appState;
        this.startPoint = _startPoint;
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
