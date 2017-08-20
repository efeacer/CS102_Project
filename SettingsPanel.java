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
import javax.swing.JSlider;

public class SettingsPanel extends JPanel
{
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image settingsLabel;
    private BufferedImage backArrow;
    private JButton backButton;
    private JSlider soundSlider;
    
    /**
     * The constructor for this panel, creates and sets images and buttons.
     */
    public SettingsPanel()
    {
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
            background = ImageIO.read( new File( "images/settingsBackground.jpg" ) );
            settingsLabel = ImageIO.read( new File( "images/settingsLabel.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    /**
     * creates and adds buttons to this panel
     */
    public void addButtons()
    {
        soundSlider = new JSlider( 0, 100, 50 );
        
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        backButton.setBounds( 20, 20, 50, 50 );
        
        backButton.setBorderPainted( false );
        backButton.setOpaque( false );
        backButton.setContentAreaFilled( false );
        
        backButton.addActionListener( new BackButtonListener() );
        
        soundSlider.setBounds( 150, 250, 700, 50 );
        add( soundSlider );
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
        g.drawImage( settingsLabel, 200, 25, 500, 100, null );
    }
    
    /**
     * inner class that is responsible for the back button
     */
    private class BackButtonListener implements ActionListener
    {
        /**
         * implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event )
        {
            Main.getStack().show( Main.getCards(), "menu" );
            //Main.setPanel( new MainMenuPanel() );
        }
    }
}
