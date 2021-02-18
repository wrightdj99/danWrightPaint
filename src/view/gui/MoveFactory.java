package view.gui;

import model.persistence.ApplicationState;
import view.gui.MoveRectangle;
import view.gui.paintPoint;

public final class MoveFactory {
    public static MoveRectangle createMove(ApplicationState _appState, paintPoint _startPoint){
        return new MoveRectangle(_appState, _startPoint);
    }
}
