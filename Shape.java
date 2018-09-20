/**
 * @Author Alex Su
 * @version 2017.11.30
 * This is a fillableshape class that contains methods about fillableshape.
 */
import java.awt.Color;
abstract public class Shape extends ShapeRoot{

    private boolean filled;
    //constructor
    public Shape(int x1,int y1,int x2,int y2, Color color, boolean filled)
    {
     super(x1,y1,x2,y2,color);
     setFilled(filled);

    }
    //return if the rectangle is filled
    public boolean getFilled()
    {
        return filled;
    }
    public void setFilled(boolean filled)
    {
        this.filled = filled;
    }
    //return the smaler X value
    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    //return the smaller Y value
    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    //return the width of the rectangle
    public int getWidth()
    {
        return Math.abs(getX2()-getX1());
    }
    //return the height of the rectangle
    public int getHeight()
    {
        return Math.abs(getY2()-getY1());
    }
    
}
