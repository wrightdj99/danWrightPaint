package view.gui;

import model.persistence.ApplicationState;
import view.gui.Rectangle;
import view.gui.paintPoint;

public final class RectangleFactory {
    //paintPoint startPoint = new paintPoint();
    public static Rectangle createRectangle(ApplicationState _appState, paintPoint startPoint){
        return new Rectangle(_appState, startPoint);
    }

    /*public paintPoint starterPoint;
    public static Rectangle createRectangle(){
        starterPoint = starterPoint;
        return new Rectangle(starterPoint);
    }*/
}
