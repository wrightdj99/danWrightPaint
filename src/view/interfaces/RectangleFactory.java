package view.interfaces;

public class RectangleFactory {
    public paintPoint StartPoint;
    public Rectangle makeRectangle(){
        return new Rectangle(StartPoint);
    }
}
