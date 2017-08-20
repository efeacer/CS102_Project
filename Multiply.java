import java.util.ArrayList;

/**
 * This class creates a multiply power-up
 * @author BROJECT
 * @version 1.0
 */
public class Multiply extends PowerUp
{
    //Constants
    private final int amount = 2;
    private final int IMPULSE_AMOUNT = 3;
    
    //Properties
    private Point impulse;
    
    /**
     * The constructor of the Multiply power-up, initializes the impulse.
     */
    public Multiply()
    {
        impulse = new Point( 0,0);
    }
    
    /**
     * The apply method of the multiply, gets the game instance as a parameter and creates a copy of every ball.
     * @param game is the instance of the game.
     */
    public void apply( GamePanel game )
    {
        
        ArrayList<Ball> extraBalls = new ArrayList<Ball>();
        for( Ball ball: game.getBalls() )
        { 
            Point tempPos = new Point( ball.getLocation().getX(), ball.getLocation().getY());
            Point tempVel = new Point( ball.getVelocity().getX(), ball.getVelocity().getY());
            Ball extraBall = ball.createDeepCopy();
            extraBall.setPosition( tempPos);
            extraBall.setVelocity( tempVel);
            
            impulse.setLocation( (int)( IMPULSE_AMOUNT * ball.getVelocity().getY() / ball.getSpeed()), 
                                - (int)( IMPULSE_AMOUNT * ball.getVelocity().getX() / ball.getSpeed()));
            
            impulse.setLocation( (int)( IMPULSE_AMOUNT * (Math.random() + 1) * ball.getVelocity().getY() / ball.getSpeed()), 
                                - (int)( IMPULSE_AMOUNT * ( Math.random() + 1) * ball.getVelocity().getX() / ball.getSpeed()));
            extraBall.accelerate( impulse);
            extraBall.move();
            
            
            impulse.setLocation( - impulse.getX(), - impulse.getY());
            ball.accelerate( impulse);
            ball.move();
            extraBalls.add( extraBall);
        }
        game.addBalls( extraBalls);
    }
}