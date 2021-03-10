package controller;

import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.EventName;
import view.gui.CopyShape;
import view.gui.MyGroup;
import view.gui.undoRedo;
import view.interfaces.*;

import java.awt.*;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvasBase paintCanvas;
    //private final MyGroup myGroup;


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
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
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
            }
        };

        IEventCallback callback2 = new IEventCallback() {
            @Override
            public void run() {
                CopyShape copyShape = paintCanvas.MyCopyShape;
                paintCanvas.MyCopyShape.MyShapeCopy();
            }
        };

        IEventCallback callback3 = new IEventCallback() {
            @Override
            public void run() {
                CopyShape copyShape = paintCanvas.MyCopyShape;
                paintCanvas.MyCopyShape.MyShapePaste();
            }
        };

        IEventCallback callback5 = new IEventCallback() {
            @Override
            public void run(){
                MyGroup myGroup = paintCanvas.myGroup;
                paintCanvas.myGroup.GroupMe();
                //paintCanvas.myGroup.draw(paintCanvas.getGraphics2D());
            }
        };

        IEventCallback callback6 = new IEventCallback() {
            @Override
            public void run() {
                MyGroup myGroup = paintCanvas.myGroup;
                paintCanvas.myGroup.MyUngroup();
            }
        };

        IEventCallback callback4 = new IEventCallback() {
            @Override
            public void run() {
                CopyShape copyShape = paintCanvas.MyCopyShape;
                paintCanvas.MyCopyShape.DeleteMe();
            }
        };

        uiModule.addEvent(EventName.UNDO, callback);
        uiModule.addEvent(EventName.REDO, callback1);
        uiModule.addEvent(EventName.COPY, callback2);
        uiModule.addEvent(EventName.PASTE, callback3);
        uiModule.addEvent(EventName.DELETE, callback4);
        uiModule.addEvent(EventName.GROUP, callback5);
        uiModule.addEvent(EventName.UNGROUP, callback6);

    }
}
