package model;

import java.util.EnumMap;

public class MouseModeMap {
    private EnumMap<MouseMode, String> MyMouseMap;
    public MouseModeMap(){
        this.MyMouseMap = new EnumMap(MouseMode.class);
        this.MyMouseMap.put(MouseMode.DRAW, "draw");
        this.MyMouseMap.put(MouseMode.SELECT, "select");
        this.MyMouseMap.put(MouseMode.MOVE, "move");
    }

    public String GetMyMouseMode(MouseMode MyMouseMode){ return this.MyMouseMap.get(MyMouseMode); }

}
