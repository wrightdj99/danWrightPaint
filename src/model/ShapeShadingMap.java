package model;

import java.awt.*;
import java.util.EnumMap;

public class ShapeShadingMap {
    private EnumMap<ShapeShadingType, Color> shadeMap;
    public ShapeShadingMap(){
        this.shadeMap = new EnumMap(ShapeShadingType.class);
        this.shadeMap.put(ShapeShadingType.OUTLINE, Color.BLACK);
        this.shadeMap.put(ShapeShadingType.FILLED_IN, Color.WHITE);
        this.shadeMap.put(ShapeShadingType.OUTLINE_AND_FILLED_IN, Color.BLACK);
    }

    public Color getMyShade(ShapeShadingType shapeShadingType){
        return this.shadeMap.get(shapeShadingType);
    }




}
