package view.gui;

import model.ShapeShadingMap;
import model.ShapeShadingType;
import model.persistence.ApplicationState;
import view.interfaces.OneShape;

import java.awt.*;

public class SelectionEllipseDecorator extends SelectionDecorator{
    public SelectionEllipseDecorator(OneShape decoratedShape){
        super(decoratedShape);
    }
    @Override
    public void draw(Graphics2D graphics2D){
        //decoratedShape.draw(graphics2D);
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawOval(this.decoratedShape.startPoint.x - 15, this.decoratedShape.startPoint.y - 15, this.decoratedShape.width + 30, this.decoratedShape.height + 30);
        graphics2D.setColor(Color.blue);
    }

    private void SelectionSpecifier(){
        System.out.println("You're selecting an Ellipse");
    }
}
