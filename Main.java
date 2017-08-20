import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.*;
import javax.sound.sampled.Clip;

/**
 * Main class of the game escape
 * @author BROHECT
 * @version 1.0
 */
public class Main 
{
    //properties
    public static SaveGame saveGame;
    
    //main method
    public static void main( String [] args )
    {
        Main game = new Main();
    }
    
    //Constants
    private static JPanel cards;
    private static CardLayout stack;
    private static JFrame mainFrame;
    private static JPanel gamePanel;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final String TITLE = "ESCAPE by BROJECT";
    
    /**
     * constructor of the Main
     */
    public Main()
    {
        saveGame = new SaveGame();
        gamePanel = new GamePanel();
        
        stack = new CardLayout();
        cards = new JPanel( stack );
        cards.add( new MainMenuPanel(), "menu" );
        cards.add( new HowToPlayPanel(), "howToPlay" );
        cards.add( gamePanel, "game" );
        cards.add( new StorePanel(), "store" );
        cards.add( new LeaderboardPanel(), "leaderboard" );   
        
        mainFrame = new JFrame( TITLE );
        mainFrame.add( cards );
        mainFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setVisible( true );
        mainFrame.setResizable( false );
        startMusic();
    }
    
    /**
     * returns the cardlayout
     * @return stack is the returned cardLayout
     */
    public static CardLayout getStack()
    {
        return stack;
    }
    
    /**
     * starts the game
     */
    public static void startGame()
    {
        ( (GamePanel) gamePanel).restartGame();
    }
    
    /**
     * returns the cards 
     * @return cards is the layout of cards to be returned
     */
    public static JPanel getCards()
    {
        return cards;
    }
    
    /**
     * returns the width of the mainframe
     * @return mainFrame is the width of the mainFrame to be returned
     */
    public static int getFrameWidth()
    {
        return FRAME_WIDTH;
    }
    
    /**
     * returns the height of the mainframe
     * @return mainFrame is the height of the mainFrame to be returned
     */
    public static int getFrameHeight()
    {
        return FRAME_HEIGHT;
    }
    
    /**
     * returns the mainframe
     * @return mainFrame is the mainFrame to be returned
     */
    public static JFrame getMainFrame()
    {
        return mainFrame;
    }
    
    /**
     * starts the music
     */
    public void startMusic()
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("music/theme.wav"));
            Clip test = AudioSystem.getClip();
            test.open(ais);
            test.start();
            test.loop( Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}