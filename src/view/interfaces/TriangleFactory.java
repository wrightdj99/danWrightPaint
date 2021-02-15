package view.interfaces;

import model.persistence.ApplicationState;

public final class TriangleFactory {
    public static Triangle createTriangle(ApplicationState _appState, paintPoint startPoint){
        return new Triangle(_appState, startPoint);
    }

    public static Triangle CloneTriangle(ApplicationState _appState, paintPoint startPoint){
        return new Triangle(_appState, startPoint);
    }
}
