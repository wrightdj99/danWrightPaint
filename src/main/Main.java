package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas);

        controller.setup();

        /*Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.blue);
        int[] xPoints = {40, 90, 40};
        int[] yPoints = {60, 50, 90};
        graphics2D.fillPolygon(xPoints, yPoints, 3);*/

        //graphics2D.fillPolygon(int[] xPoints = {40, 50, 30}, int[] yPoints = {60, 70, 20}, 3);
        //graphics2D.drawPolygon(new int[] {10, 20, 20}, new int[] {50, 60, 70}, 3);
        //Graphics2D graphics2D = paintCanvas.getGraphics2D();
        //graphics2D.drawString("Dan's Paint Application", 30,30);
        //paintCanvas.drawDummyRectangle();

        // For example purposes only; remove all lines below from your final project.

/*        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Filled in rectangle
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(12, 13, 200, 400);
        //graphics2d.fillRect(45, 39, 45, 45);
        // Outlined rectangle
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(12, 13, 200, 400);


        // Selected Shape
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410);*/
    }
}
