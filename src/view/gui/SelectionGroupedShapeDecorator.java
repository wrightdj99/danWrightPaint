package view.gui;

import view.interfaces.OneShape;

import java.awt.*;
import java.util.ArrayList;

public class SelectionGroupedShapeDecorator extends SelectionDecorator{
    public SelectionGroupedShapeDecorator(OneShape decoratedShape, boolean _amIPartOfAGroup){
        super(decoratedShape, _amIPartOfAGroup);
    }
    @Override
    public void draw(Graphics2D graphics2D, ArrayList<OneShape> registeredShapes){
        //decoratedShape.draw(graphics2D);
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.RED);
        graphics2D.drawRect(this.decoratedShape.startPoint.x - 15, this.decoratedShape.startPoint.y - 15, this.decoratedShape.width + 30, this.decoratedShape.height + 30);
        graphics2D.setColor(Color.pink);
    }

    private void SelectionSpecifier(){
        System.out.println("You're selecting a Rectangle");
    }
}
