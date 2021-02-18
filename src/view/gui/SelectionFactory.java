package view.gui;

import model.persistence.ApplicationState;
import view.gui.SelectionRectangle;
import view.gui.paintPoint;

public final class SelectionFactory {
    public static SelectionRectangle CreateSelection(ApplicationState _appState, paintPoint startPoint){
        return new SelectionRectangle(_appState, startPoint);
    }
}
