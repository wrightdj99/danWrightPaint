package model;

import java.awt.*;
import java.util.EnumMap;

public class ShapeColorMap {
    //private ShapeColorMap myShapeColor;
    private EnumMap<ShapeColor, Color> MyMap;
    public ShapeColorMap(){
        this.MyMap = new EnumMap(ShapeColor.class);
        this.MyMap.put(ShapeColor.BLUE, Color.BLUE);
        this.MyMap.put(ShapeColor.GREEN, Color.GREEN);
        this.MyMap.put(ShapeColor.CYAN, Color.CYAN);
        this.MyMap.put(ShapeColor.PINK, Color.PINK);
        this.MyMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        this.MyMap.put(ShapeColor.GRAY, Color.GRAY);
        this.MyMap.put(ShapeColor.BLACK, Color.BLACK);
        this.MyMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        this.MyMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        this.MyMap.put(ShapeColor.ORANGE, Color.ORANGE);
        this.MyMap.put(ShapeColor.RED, Color.RED);
        this.MyMap.put(ShapeColor.WHITE, Color.WHITE);
        this.MyMap.put(ShapeColor.YELLOW, Color.YELLOW);
    }

    public Color getMyPrimaryShapeColor(ShapeColor shapeColor){
        return this.MyMap.get(shapeColor);
    }
    public Color getMySecondaryShapeColor(ShapeColor shapeColor) { return this.MyMap.get(shapeColor); }

}
