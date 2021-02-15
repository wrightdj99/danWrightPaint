package view.interfaces;

import model.persistence.ApplicationState;

public final class EllipseFactory {
    public static Ellipse createEllipse(ApplicationState _appState, paintPoint startPoint){
        return new Ellipse(_appState, startPoint);
    }

}
