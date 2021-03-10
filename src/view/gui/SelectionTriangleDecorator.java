package view.gui;

import view.interfaces.OneShape;

import java.awt.*;
import java.util.ArrayList;

public class SelectionTriangleDecorator extends SelectionDecorator{
    public SelectionTriangleDecorator(OneShape decoratedShape, boolean _amIPartOfAGroup){
        super(decoratedShape, _amIPartOfAGroup);
    }

    @Override
    public void draw(Graphics2D graphics2D, ArrayList<OneShape> registeredShapes){
        //decoratedShape.draw(graphics2D);
        if (!this.AmIPartOfAGroup) {
            Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
            graphics2D.setStroke(stroke);
            graphics2D.setColor(Color.BLACK);
            int funnyX = (int) Math.round((float) this.decoratedShape.width / (float) this.decoratedShape.height * 10D);
            int funnyY = (int) Math.round((float) this.decoratedShape.height / (float) this.decoratedShape.width * 10D);
            int[] xPoints = {this.decoratedShape.startPoint.x - 5, this.decoratedShape.endPoint.x + funnyX, this.decoratedShape.startPoint.x - 5};
            int[] yPoints = {this.decoratedShape.startPoint.y - funnyY, this.decoratedShape.endPoint.y + 5, this.decoratedShape.endPoint.y + 5};
            graphics2D.drawPolygon(xPoints, yPoints, 3);
        }
        graphics2D.setColor(Color.pink);
    }

    private void SelectionSpecifier(){
        System.out.println("You're selecting an Ellipse");
    }
}
