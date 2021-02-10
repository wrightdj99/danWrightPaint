package view.interfaces;

import model.persistence.ApplicationState;

public final class SelectionFactory {
    public static SelectionRectangle CreateSelection(ApplicationState _appState, paintPoint startPoint){
        return new SelectionRectangle(_appState, startPoint);
    }
}
