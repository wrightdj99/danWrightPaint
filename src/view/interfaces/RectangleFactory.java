package view.interfaces;

public final class RectangleFactory {
    paintPoint startPoint = new paintPoint();
    public static Rectangle createRectangle(paintPoint startPoint){
        return new Rectangle(startPoint);
    }

    /*public paintPoint starterPoint;
    public static Rectangle createRectangle(){
        starterPoint = starterPoint;
        return new Rectangle(starterPoint);
    }*/
}
