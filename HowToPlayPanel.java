import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import java.util.ArrayList;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

/**
 * The how to play panel of escape
 * @author BROJECT
 * @version 1.0
 */
public class HowToPlayPanel extends JPanel 
{
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image howToPlayLabel;
    private BufferedImage backArrow;
    private JButton backButton;
    private BufferedImage first;
    private BufferedImage second;
    private BufferedImage third;
    private ArrayList<BufferedImage> instructions;
    private int index;
    private BufferedImage rightArrow;
    private BufferedImage leftArrow;
    private JButton rightButton;
    private JButton leftButton;
    
    /**
     * The constructor for this panel, creates and sets images and buttons.
     */
    public HowToPlayPanel()
    {
        super(true);
        instructions = new ArrayList<BufferedImage>();
        
        setLayout( null );
        
        addImages();
        
        addButtons();
    }
    
    /**
     * adds images to this panel 
     */
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "images/howToPlayBackground.jpg" ) );
            howToPlayLabel = ImageIO.read( new File( "images/howToPlayLabel.png" ) );
            leftArrow = ImageIO.read( new File( "images/leftArrow.png" ) );
            rightArrow = ImageIO.read( new File( "images/rightArrow.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
            second = ImageIO.read(new File( "images/second.jpeg" ) );
            third = ImageIO.read(new File( "images/third.jpeg" ) );
            first = ImageIO.read(new File( "images/fourth.png" ) );
            instructions.add(first);
            instructions.add(second);
            instructions.add(third);
        }
        
        catch( IOException exception ){}
    }
    
    /**
     * creates and adds buttons to this panel
     */
    public void addButtons()
    {
        rightButton = new JButton( new ImageIcon( rightArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        leftButton = new JButton( new ImageIcon( leftArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        rightButton.setBounds(  BACKGROUND_WIDTH - 125, 250, 50, 50 );
        leftButton.setBounds( 75, 250, 50, 50 ); 
        backButton.setBounds( 20, 20, 50, 50 );
        
        rightButton.setBorderPainted( false );
        rightButton.setContentAreaFilled( false );
        rightButton.setOpaque( false );
        leftButton.setBorderPainted( false );
        leftButton.setContentAreaFilled( false );
        leftButton.setOpaque( false );
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setOpaque( false );
        
        rightButton.addActionListener( new ArrowListener() );
        leftButton.addActionListener( new ArrowListener() );
        backButton.addActionListener( new BackButtonListener() );
        
        add( rightButton );
        add( leftButton );
        add( backButton );
    }
    
    
    /**
     * Paints the components of this panel, gets a Graphics object as a parameter.
     * @param g is the Graphics object.
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( howToPlayLabel, 160, 25, 600, 100, null );
        if(instructions.get(index) == first)
        {
            g.drawImage( first, 200, 130, 500, 400, null );
        }
        else if(instructions.get(index) == second)
        {
            g.drawImage( second, 190, 130 , 520, 400, null );
        }
        else if(instructions.get(index) == third)
        {
            g.drawImage( third, 250, 130 , 400, 400, null );
        }  
    }
    
    /**
     * inner class that is responsible for the back button
     */
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.getStack().show( Main.getCards(), "menu" );
            //Main.setPanel( new MainMenuPanel() );
        }
    }
    
    /**
     * inner class that is responsible for the arrow buttons
     */
    private class ArrowListener implements ActionListener
    {
        /**
         * implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event )
        {
            if ( event.getSource() == rightButton )
            {
                if ( index == instructions.size() - 1 )
                {
                    index = 0;
                }
                else
                {
                    index = index + 1;
                }
                repaint();
            }
            else if ( event.getSource() == leftButton )
            {
                if ( index == 0 )
                {
                    index = instructions.size() - 1;
                }
                else
                {
                    index = index - 1;
                }
                repaint();
            }
        }
    }
}