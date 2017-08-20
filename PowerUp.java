import java.util.ArrayList;

/**
 * This abstract class represents a power-up
 * @author BROJECT
 * @version 1.0
 */
public abstract class PowerUp
{
    //Constants
    private final int RADIUS = 15;
    private final int TIME_TO_LIVE = 5;
    
    //Properties
    private Point location;
    private int timeAlive;
    private boolean isActive;
    
    /**
     * The constructor, creates a PowerUp on 0, 0.
     */
    public PowerUp()
    {
        location = new Point();
        timeAlive = 0;
        isActive = false;
    } 
    
    /** 
     * Creates a PowerUp on the given x, y coordinate
     * @param x is the x coordinate
     * @param y is the y coordinate
     */
    public PowerUp( int x, int y)
    {
        location.setLocation( x, y);
    }
    
    /**
     * Determines whether a power-up is taken or not, returns a boolean indicating it.
     * @return is the relevant boolean 
     */
    public boolean isTaken( ArrayList<Ball> balls )
    {
        for( Ball ball: balls)
        {
            if ( ball.getRadius() + this.RADIUS >= this.location.getDistance( ball.getLocation()))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the location of a power-up.
     * @return location is the location of the power-up.
     */
    public Point getLocation()
    {
        return location;
    }
    
    /**
     * Returns the radius of a power-up.
     * @return RADIUS is the radius of the power-up.
     */
    public int getRadius()
    {
        return RADIUS;
    }
    
    /**
     * Increments the time that the power-up exists.
     */
    public void incrementTimeAlive( int amount)
    {
        timeAlive = timeAlive + amount;
    }
    
    /**
     * Checks whether a power-up exists or not, returns a relevant boolean.
     * @return is the relevant boolean.
     */
    public boolean isAlive()
    {
        return TIME_TO_LIVE >= timeAlive;
    }
    
    /**
     * Checks whether a power-up is active or not, returns a relevant boolean.
     * @return is the relevant boolean.
     */
    public boolean isActive()
    {
        return isActive;
    }
    
    /**
     * Sets a power-up active if it is not active.
     * @param check is the boolean expression indicating the active state of the power-up.
     */
    public void setActive( boolean check)
    {
        isActive = check;
    }
    
    /**
     * The abstract apply method, that will be implemented by the subclasses.
     * @param game is the game instance.
     */
    public abstract void apply( GamePanel game);
}