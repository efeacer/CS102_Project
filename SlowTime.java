import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 

/**
 * This class represents a SlowTime power-up
 * @author BROJECT
 * @version 1.0
 */
public class SlowTime extends PowerUp
{
    //Constants
    private final double AMOUNT = 0.7;
    private final int DURATION = 4500;
    
    /**
     * The apply method of the SlowTime, slows down the time.
     * @param game is the instance of the game.
     */
    public void apply( GamePanel game)
    {
        super.setActive( true);
        ArrayList<Ball> temp = new ArrayList<Ball>();
        for( Ball ball: game.getBalls()) 
        {
            ball.multiplyVelocity( AMOUNT);
            temp.add(ball);
        }
        game.setBalls( temp);
        game.setPace( game.getPace() * AMOUNT);
        
        Timer timer = new Timer( DURATION, new TimerListener( game));
        timer.setRepeats( false);
        timer.start();
    }
    
    /**
     * The inner class, which is the timer.
     */
    private class TimerListener implements ActionListener
    {        
        private GamePanel game;
        
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
        public void actionPerformed( ActionEvent e)
        {
            ArrayList<Ball> temp = new ArrayList<Ball>();
            
            for( Ball ball: game.getBalls()) 
            {
                ball.multiplyVelocity( 1 / AMOUNT);
                temp.add(ball);
            }
            game.setBalls( temp);
            game.setPace( game.getPace() / AMOUNT);
            setActive( false);
        }
    }
}