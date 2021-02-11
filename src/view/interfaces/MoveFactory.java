package view.interfaces;

import model.persistence.ApplicationState;

public final class MoveFactory {
    public static MoveRectangle createMove(ApplicationState _appState, paintPoint _startPoint){
        return new MoveRectangle(_appState, _startPoint);
    }
}
