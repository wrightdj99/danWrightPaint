package view.gui;

import model.persistence.ApplicationState;

public final class SelectionFactory {
    public static RectangleSelection CreateSelection(ApplicationState _appState, paintPoint startPoint){
        return new RectangleSelection(_appState, startPoint);
    }
}
