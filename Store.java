import java.util.ArrayList;

/**
 * Store class for Escape game
 * @author BROJECT
 * @version 1.0
 */
public class Store
{
    //properties
    private static ArrayList<Ball> balls;
    
    /**
     * The constructor for the Store, initializes the list of balls.
     */
    public Store()
    {
        balls = new ArrayList<Ball>();
        balls.add( new SunBall() );
        balls.add( new MoonBall() );
        balls.add( new EarthBall() );
    }
    
    /**
     * Returns the list of balls
     * @return balls is the list of balls.
     */
    public static ArrayList<Ball> getBalls()
    {
        return balls;
    }
}