import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/**
 * This is a super paint program that allow users to use the functions included in this program to 
 * paint from the three shapes(line,oval,rectangle), with filled or unfilled options and option to undo,redo,and clear
 * @author Alex Su
 * @version 2018.5.2
 */
public class DrawFrame extends JFrame {
    private JLabel statusLabel;
    private DrawPanel drawPanel;
    private JPanel topJPanel;
    private JPanel flowJPanel;
    private JButton undo;
    private JButton redo;
    private JButton clear;
    private JComboBox<String> colorChooser;
    private String[] colorNames = { "Black","Blue","Cyan","Dark Gray","Gray","Green","Light Gray","Magenta","Orange","Pink"};
    private Color[] colorValues = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, };
    private JComboBox<String> shapeChooser;
    private String[] shapeNames = { "Line", "Oval", "Rectangle" };
    private int[] shapeValues = { 0, 1, 2 };
    private Color color;
    private int type;
    
    private JCheckBox filledBox;
    
    public DrawFrame() { 
        super( "SuperPaint Application!" );
        
        // Put statusLabel in SOUTH to display mouse coordinates
        statusLabel = new JLabel();
        add( statusLabel, BorderLayout.SOUTH );
        
        // topJPanel for different functions and options
        topJPanel = new JPanel();
        topJPanel.setLayout( new GridLayout( 1, 6 ) );
        flowJPanel = new JPanel();
        flowJPanel.setLayout( new FlowLayout() );
        
        // initialize buttons
        undo = new JButton( "undo" );
        redo = new JButton( "redo" );
        clear = new JButton( "clear" );
        // initialize checkbox
        filledBox = new JCheckBox( "Filled" );

        
        // Button eventListener
        ActionListener eventListener = new ButtonEventListener();
        undo.addActionListener( eventListener );
        redo.addActionListener( eventListener );
        clear.addActionListener( eventListener );
        
        // initialize colorChooser
        colorChooser = new JComboBox<String>( colorNames );
        colorChooser.setMaximumRowCount( 4 );
        
        //initialize shapeChooser
        shapeChooser = new JComboBox<String>( shapeNames );
        shapeChooser.setMaximumRowCount( 3 );
        
        //initialize eventListenter
        ItemListener comboBoxListener = new ComboBoxEventListener();
        ItemListener checkBoxListener = new CheckBoxEventListener();
        colorChooser.addItemListener( comboBoxListener );
        shapeChooser.addItemListener( comboBoxListener );
        filledBox.addItemListener( checkBoxListener );
        // Add all the things to topJPanel
        topJPanel.add(undo );
        topJPanel.add(redo );
        topJPanel.add(clear );
        topJPanel.add( colorChooser );
        topJPanel.add( shapeChooser );
        topJPanel.add( filledBox );
        
        // Add topJPanel to the flowJPanel
        flowJPanel.add( topJPanel );
        add( flowJPanel, BorderLayout.NORTH );
        
        // Put DrawPanel in CENTER and pass reference to statusLabel for updates
        drawPanel = new DrawPanel( statusLabel );
        add( drawPanel );
        
    }
    
    // Inner class for handling events on buttons
    class ButtonEventListener implements ActionListener {
        @Override 
        public void actionPerformed( ActionEvent e ) {
            
            if ( e.getSource() == undo ) {
                drawPanel.undo();
            }
            else if ( e.getSource() == redo ) {
                drawPanel.redo();
            }
            else if (e.getSource() == clear ) {
                drawPanel.clear();
            }
        }
    }
    
    // Inner class for handling events on combo boxes
    class ComboBoxEventListener implements ItemListener {
        @Override
        public void itemStateChanged( ItemEvent e ) {
            drawPanel.setColor( colorValues[ colorChooser.getSelectedIndex() ] );
            drawPanel.setType( shapeValues[ shapeChooser.getSelectedIndex() ] );
        }
    }
    
    // Inner class for handling events on check box
    class CheckBoxEventListener implements ItemListener {
        @Override 
        public void itemStateChanged( ItemEvent e ) {
            //If the filled box is clicked, change the filled setting to on.
            if ( filledBox.isSelected() ) 
                drawPanel.setFilled( true );
            else 
                drawPanel.setFilled( false );
        }         
    }
    
}
