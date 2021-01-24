package view.interfaces;

public class Rectangle implements myShape{

    int endHeight, endWidth;

    Rectangle(int endHeight, int endWidth){
        this.endHeight = endHeight;
        this.endWidth = endWidth;
    }

    public int getPointHeight(){
        return endHeight;
    }

    public int getPointWidth(){
        return endWidth;
    }
}
