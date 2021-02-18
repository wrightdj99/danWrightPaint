package view.gui;

import model.persistence.ApplicationState;
import view.gui.Ellipse;
import view.gui.paintPoint;

public final class EllipseFactory {
    public static Ellipse createEllipse(ApplicationState _appState, paintPoint startPoint){
        return new Ellipse(_appState, startPoint);
    }

}
