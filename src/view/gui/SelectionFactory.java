package view.gui;

import model.persistence.ApplicationState;

public final class SelectionFactory {
    public static ShapeSelection CreateSelection(ApplicationState _appState, paintPoint startPoint){
        return new ShapeSelection(_appState, startPoint);
    }
}
