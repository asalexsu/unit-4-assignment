import java.awt.Color;
import java.awt.Graphics;
public class Oval extends Shape {


    // Constructor to initialize all instance variables
    public Oval( int x1, int y1, int x2, int y2, Color color, boolean filled ) {
        super(x1,y1,x2,y2,color,filled); 
    } 
    
    // Given a Graphics object (g), this method will draw the current Line object
    public void draw( Graphics g ) {
        g.setColor( getColor() );

        if (getFilled())
            g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
        else
            g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() );
    } 
} 