import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import java.awt.Graphics;

/**
 * This class represents a shield power-up
 * @author BROJECT
 * @version 1.0
 */
public class Shield extends PowerUp
{       
    //Constants
    private final int DURATION = 4000;
    
    //Properties
    private Timer timer;
    
    /**
     * The apply method of the Shield, gets the game instance as a parameter and protects every ball from the lasers.
     * @param game is the instance of the game.
     */
    public void apply( GamePanel game)
    {
        setActive( true);
        for( Ball ball: game.getBalls()) 
        {
            ball.setTouchable( false);
        }
        timer = new Timer( DURATION , new TimerListener( game ));
        timer.setRepeats( false);
        timer.start();
    }
    
    /**
     * The inner class, which is the timer.
     */
    private class TimerListener implements ActionListener
    {        
        private GamePanel game;
        private Graphics g;
        
        /**
         * The constructor of the TimerListener, takes a game instance as a parameter.
         * @param game is the game instance.
         */
        public TimerListener( GamePanel game)
        {
            this.game = game;
        }
        
        /**
         * The overriden actionPerformed method, takes the ActionEvent as a parameter.
         * @param e is the ActionEvent.
         */
        public void actionPerformed( ActionEvent e )
        {
            setActive( false );
            if ( !game.getActive().isActive())
            {
                for( Ball ball: game.getBalls() ) 
                {
                    ball.setTouchable( true);       
                }
            }
        }
    }
}