package model;

import view.interfaces.Ellipse;
import view.interfaces.OneShape;

import java.awt.*;
import java.util.EnumMap;

public class ShapeTypeMap {
    private EnumMap<ShapeType, String> shapeMap;
    public ShapeTypeMap(){

        this.shapeMap = new EnumMap(ShapeType.class);
        this.shapeMap.put(ShapeType.ELLIPSE, "ellipse");
        this.shapeMap.put(ShapeType.RECTANGLE, "rectangle");
        this.shapeMap.put(ShapeType.TRIANGLE, "triangle");
    }
    public String getMyShape(ShapeType shapeType){
        return this.shapeMap.get(shapeType);
    }

}
