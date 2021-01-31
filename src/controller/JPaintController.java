package controller;

import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.EventName;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;
import view.interfaces.Rectangle;
import view.interfaces.undoRedo;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvasBase paintCanvas;


    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvasBase paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        this.paintCanvas.setMyAppState((ApplicationState) this.applicationState);
        this.paintCanvas.GetMyClickHandler().setMyAppState(this.paintCanvas.myAppState);
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        //Sprint 2 Starting Task
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        //uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> System.out.println("Hello World"));
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());

        IEventCallback callback = new IEventCallback() {
            @Override
            public void run() {
                undoRedo myUndoRedo = paintCanvas.myUndoRedo;
                myUndoRedo.myUndo();

            }
        };

        IEventCallback callback1 = new IEventCallback() {
            @Override
            public void run() {
                undoRedo myUndoRedo1 = paintCanvas.myUndoRedo;
                myUndoRedo1.myRedo();
                //System.out.println("cba");
            }
        };

        uiModule.addEvent(EventName.UNDO, callback);
        uiModule.addEvent(EventName.REDO, callback1);
        //uiModule.addEvent(EventName.UNDO, () -> );

        /*IEventCallback callback = new IEventCallback() {
            @Override
            public void run() {
                //ICommand cmd = new undoRedo;

            }
        }*/
    }
}
