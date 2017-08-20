import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The menu panel of escape
 * @author BROJECT
 * @version 1.0
 */
public class MainMenuPanel extends JPanel 
{ 
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image logo;
    private JButton playButton;
    private JButton storeButton;
    private JButton leaderboardButton;
    private JButton howToPlayButton; 
    private BufferedImage playImage;
    private BufferedImage storeImage;
    private BufferedImage leaderboardImage;
    private BufferedImage howToPlayImage;
    
    /**
     * The constructor for this panel, creates and sets images and buttons.
     */
    public MainMenuPanel()
    {
        setLayout(null);
        
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
            background = ImageIO.read( new File( "images/menuBackground.jpg") );
            logo = ImageIO.read( new File( "images/logo.png" ) );
            playImage = ImageIO.read( new File( "images/play.png" ) );
            storeImage = ImageIO.read( new File( "images/store.png" ) );
            leaderboardImage = ImageIO.read( new File( "images/leaderboard.png" ) );
            howToPlayImage = ImageIO.read( new File( "images/howToPlay.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    /**
     * creates and adds buttons to this panel
     */
    public void addButtons()
    {
        playButton = new JButton( new ImageIcon( playImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        storeButton = new JButton( new ImageIcon( storeImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        leaderboardButton = new JButton( new ImageIcon( leaderboardImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        howToPlayButton = new JButton( new ImageIcon( howToPlayImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        
        playButton.setBounds( 300, 210, 300, 50 );
        storeButton.setBounds( 300, 270, 300, 50 );
        leaderboardButton.setBounds( 300, 330, 300, 50);
        howToPlayButton.setBounds( 300, 390, 300, 50 );
        
        playButton.addActionListener( new ClickListener() );
        storeButton.addActionListener( new ClickListener() );
        leaderboardButton.addActionListener( new ClickListener() );
        howToPlayButton.addActionListener( new ClickListener() );
        
        playButton.setBorderPainted( false );
        playButton.setOpaque( false );
        playButton.setContentAreaFilled( false );
        storeButton.setBorderPainted( false );
        storeButton.setOpaque( false );
        storeButton.setContentAreaFilled( false );
        leaderboardButton.setBorderPainted( false );
        leaderboardButton.setOpaque( false );
        leaderboardButton.setContentAreaFilled( false );
        howToPlayButton.setBorderPainted( false );
        howToPlayButton.setOpaque( false );
        howToPlayButton.setContentAreaFilled( false );
        
        add( playButton );
        add( storeButton );
        add( leaderboardButton );
        add( howToPlayButton );
    }
    
    
    /**
     * Paints the components of this panel, gets a Graphics object as a parameter.
     * @param g is the Graphics object.
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( logo, 150, 25, 600, 150, null );
    }
    
    /**
     * inner class that is responsible for the clicks
     */
    private class ClickListener implements ActionListener
    {
        /**
         * The implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event ) 
        {
            if ( ( JButton )event.getSource() == playButton )
            {
                Main.getStack().show( Main.getCards(), "game" );
                Main.startGame();
            }
            else if ( ( JButton )event.getSource() == storeButton )
            {
                Main.getStack().show( Main.getCards(), "store" );
            }
            else if ( ( JButton )event.getSource() == leaderboardButton )
            {
                Main.getStack().show( Main.getCards(), "leaderboard" );
            }
            else if ( ( JButton )event.getSource() == howToPlayButton )
            {
                Main.getStack().show( Main.getCards(), "howToPlay" );
            }
        }
    }
}
