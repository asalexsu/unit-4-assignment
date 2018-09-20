import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * This is a super paint program that allow users to use the functions included in this program to 
 * paint from the three shapes(line,oval,rectangle), with filled or unfilled options and option to undo,redo,and clear
 * @author Alex Su
 * @version 2018.5.2
 */
public class DrawPanel extends JPanel{
    private JLabel statusBar;
    private Shape currentShape;
    private LinkedList<Shape> shapes;
    private LinkedList<Shape> tempShapes;
    private Shape copyShapes;
    private int type;
    private boolean filled;
    private Color color;
    

    public DrawPanel( JLabel statusLabel ) {
        statusBar = statusLabel;
        setBackground( Color.WHITE );
        shapes = new LinkedList<>();
        tempShapes = new LinkedList<>();
        type = 0;
        color = Color.BLACK;
        
        // Create and register listener for mouse and mouse motion events
        MouseEventListener drawPanelListener = new MouseEventListener(); 
        addMouseListener( drawPanelListener ); 
        addMouseMotionListener( drawPanelListener );       
    }
    
        // Inner class to handle mouse events
        class MouseEventListener extends MouseAdapter {
            // Mouse press indicates a new shape has been started
            @Override
            public void mousePressed( MouseEvent event ) {
                // Identify the type of shape and draw it
                 switch (type) {
                    case 0: currentShape = new Line( event.getX(), event.getY(), event.getX(), event.getY(), Color.RED );
                    break;
                    case 1: currentShape = new Oval( event.getX(), event.getY(), event.getX(), event.getY(), Color.RED, filled  );
                    break;
                    case 2: currentShape = new Rectangle( event.getX(), event.getY(), event.getX(), event.getY(), Color.RED, filled );
                    break;
                }
                tempShapes.clear();
                // Tell JVM to call paintComponent( g )
                repaint();
            }
            
            // Mouse release indicates the new line is finished
            @Override
            public void mouseReleased( MouseEvent event ) {
                // Update ending coordinates and switch color to the color to BLACK
                currentShape.setX2( event.getX() );
                currentShape.setY2( event.getY() );
                currentShape.setColor( color );
                // Push the new shape into the linkedList
                shapes.addFirst( currentShape );
                // set the currentShape variable to null
                currentShape = null;
                repaint();      
            }
            
            // As mouse is dragged, update ending coordinates of currentShape and statusBar
            @Override
            public void mouseDragged( MouseEvent event ) {
                currentShape.setX2( event.getX() );
                currentShape.setY2( event.getY() );
                statusBar.setText( String.format( "Mouse at (%d, %d)", 
                                                 event.getX(), event.getY() ) );
                repaint();
            } 
            
            // As mouse is moved, just update the statusBar
            @Override
            public void mouseMoved( MouseEvent event ) {
                statusBar.setText( String.format( "Mouse at (%d, %d)", 
                                                 event.getX(), event.getY() ) );
            }   
        }
        
         // This method is called automatically by the JVM when the window needs to be (re)drawn.
        @Override
        public void paintComponent( Graphics g ) {
            super.paintComponent( g );
            // Call the draw() method for each Shape object in the linkedList
            for (int i=0;i<shapes.size();i++){
                copyShapes=shapes.removeLast();
                copyShapes.draw(g);
            shapes.addFirst(copyShapes);
            }
            // Display the shapes within the stack
            // If a line is in progress, draw it on top of all others
            if ( currentShape != null ) {
                currentShape.draw( g );
            }
        }
        

        
        // this method will set the filled setting
        public void setFilled( boolean shapeFilled ) {
            filled = shapeFilled;
        }
        
        // this method will set the shape type
        public void setType( int shapeType) {
            type = shapeType;
        }        
        
        // This method will set the color
        public void setColor( Color shapeColor ) {
            color = shapeColor;

        }
        
        // This method will undo the last step
        public void undo() {
            if(!shapes.isEmpty())
                tempShapes.addFirst(shapes.removeFirst());
            repaint();
            
        }
        
        // This method will redo the last step
        public void redo() {
            if ( !tempShapes.isEmpty()) {
                shapes.addFirst( tempShapes.removeFirst() );
            }
            repaint();
        }
        
        // this method clears the linkedLists and clear the screen
        public void clear() {
            shapes.clear();
            tempShapes.clear();
            repaint();
        }
        
        
        
}