/**
 * @Author Alex Su
 * @version 2017.11.30
 * This is a fillableshape class that contains methods about fillableshape.
 */
import java.awt.Color;
import java.awt.Graphics;
abstract public class ShapeRoot {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    //constructor
    public ShapeRoot(int x1,int y1,int x2,int y2, Color color)
    {
        setX1(x1);
        setX2(x2);
        setY1(y1);
        setY2(y2);
        setColor(color);
    }
    //return x1
    public int getX1()
    {
        return x1;
    }
    //return x2
    public int getX2()
    {
        return x2;
    }
    //return y1
    public int getY1()
    {
        return y1;
    }
    //return y2
    public int getY2()
    {
        return y2;
    }
    //return if the rectangle is filled
    public Color getColor()
    {
        return color;
    }
    //set X1 with sanity check
    public void setX1(int newX1)
    {
        if (newX1>=0)   
        {
            x1 = newX1;
        }
        else
        {
            System.err.println( "Attempt to set x1 smaller than 0 ignored, setting to 0 by default." );
            x1 = 0;
        }
    }
    //set X2 with sanity check
    public void setX2(int newX2)
    {
        if (newX2>=0)   
        {
            x2 = newX2;
        }
        else
        {
            System.err.println( "Attempt to set x2 smaller than 0 ignored, setting to 0 by default." );
            x2 = 0;
        }
    }
    //set Y1 with sanity check
    public void setY1(int newY1)
    {
        if (newY1>=0)   
        {
            y1 = newY1;
        }
        else
        {
            System.err.println( "Attempt to set y1 smaller than 0 ignored, setting to 0 by default." );
            y1 = 0;
        }
    }
    //set Y2 with sanity check
    public void setY2(int newY2)
    {
        if (newY2>=0)   
        {
            y2 = newY2;
        }
        else
        {
            System.err.println( "Attempt to set g2 smaller than 0 ignored, setting to 0 by default." );
            y2 = 0;
        }
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    abstract public void draw( Graphics g );
    //return the smaler X value
    
    
}
