package view.interfaces;

import java.awt.*;
import java.util.ArrayList;

public class undoRedo {
    public undoRedo(PaintCanvasBase _pCV){

        this.paintCanvasBase = _pCV;
        this.registeredRect =  new ArrayList<Rectangle>();
        this.unregisteredRect  = new ArrayList<Rectangle>();
    }

    public ArrayList<Rectangle> registeredRect;
    public ArrayList<Rectangle> unregisteredRect;

    PaintCanvasBase paintCanvasBase;

    public void myUndo(){
        if(this.registeredRect.size() == 0){
            System.out.println("Registered Shapes is empty");
        }
        else{
            //System.out.println("Before " + this.registeredRect.size());
            Rectangle removedRectangle = this.registeredRect.remove(this.registeredRect.size() - 1);
            System.out.println("Done");
            //System.out.println("After " + this.registeredRect.size());
        }

        paintCanvasBase.drawAllRectangles();


        //System.out.println("Our size " + this.registeredRect.size());
        //If statement for button being pressed then...
        /*if(registeredRect.size() > 0){
            Rectangle removedRectangle = this.registeredRect.remove(this.registeredRect.size() - 1);
            this.unregisteredRect.add(removedRectangle);
            //System.out.println("Size of registeredRect is: " + .registeredRect.size());
        }*/

        //System.out.println("Size of registeredRect is: " + this.registeredRect.size());

        //Rectangle removedRectangle = this.registeredRect.remove(this.registeredRect.size() - 1);
        //this.unregisteredRect.add(removedRectangle);
    }

    public void myRedo(){
        //System.out.println("Size of unregisteredRect is: " + this.unregisteredRect.size());
        /*if(this.unregisteredRect.size() > 0){
            Rectangle reintroduedRectangle = this.unregisteredRect.remove(this.unregisteredRect.size() - 1);
            this.registeredRect.add(reintroduedRectangle);
            reintroduedRectangle.draw(paintCanvasBase.getGraphics2D());
        }
        System.out.println("Size of unregisteredRect is: " + this.unregisteredRect.size());*/
        //Rectangle reintroduedRectangle = this.unregisteredRect.remove(this.unregisteredRect.size() - 1);
        //this.registeredRect.add(reintroduedRectangle);

        paintCanvasBase.redrawRectangle();
    }

    public void addShape(){

    }

    public void deleteShape(){

    }
}
