/**
 * This class represents a Laser in the game. The Laser consists of the Points and a line connecting them. Thus, this
 * class involves methods to form this line segment and access relevant information.
 * @author BROJECT
 * @version 1.0
 */
public class Laser
{
    //Properties
    private Point p1;
    private Point p2;
    private double timeAlive;
    private static double timeToLive;
    private double length;
    private double slope; //additional
    
    /**
     * The default constructor of the Laser. Produces a line segment of random position and random length.
     */
    public Laser()
    {
        p1 = new Point();
        
        int xTranslation;
        int yTranslation;
        double x2;
        double y2;
        
        xTranslation = 180 + (int) ( Math.random() * 81 );
        yTranslation = 180 + (int) ( Math.random() * 81 );
        
        int chance = ( int ) Math.pow( - 1, ( int ) ( Math.random() * 2 ) );
        
        if ( Math.random() > 0.5 )
        {
            x2 = p1.getX() + xTranslation;
            y2 = p1.getY() + chance * yTranslation;
        }
        
        else
        {
            x2 = p1.getX() +  chance * xTranslation;
            y2 = p1.getY() + yTranslation;
        }
        
        p2 = new Point( x2, y2 );
        
        slope = ( p2.getY() - p1.getY() ) / ( p2.getX() - p1.getX() );
        timeAlive = 0;
        timeToLive = 10; //or another constant, it should be determined
    }
    
    /**
     * Determines whether a specified Ball and the Laser intersects or not, using the fundamentals of analytic 
     * geometry. Returns true if there is an intersection, returns false otherwise.
     * @param toCheck is the specified Ball that will be checked for intersection.
     * @return is the boolean expression indicating whether an intersection occurs or not.
     */
    public boolean isTouched( Ball toCheck )
    {
        Point segV;
        Point ptV;
        Point segVUnit;
        Point projV;
        Point closest;
        Point distV;
        double proj;
        
        if ( !toCheck.isTouchable() )
        {
            return false;
        }
        
        if (  toCheck.getLocation().getDistance( p2 ) <= toCheck.getRadius()
                || toCheck.getLocation().getDistance( p1 ) <= toCheck.getRadius() )
        {
            return true;
        }
        
        segV = p2.dif( p1 );
        ptV = toCheck.getLocation().dif( p1 );
        segVUnit = new Point();
        
        if ( segV.getDistanceToOrigin() > 0 )
        {
            segVUnit = segV.scalar( 1 / segV.getDistanceToOrigin() );
        }
        else
        {
            return false;
        }
        
        proj = ptV.dotProduct( segVUnit );
        
        if ( proj <= 0 || proj >= segV.getDistanceToOrigin() )
        {
            return false;
        }
        
        projV = segVUnit.scalar( proj );
        closest = projV.sum( p1 );
        distV = toCheck.getLocation().dif( closest );
        
        if ( distV.getDistanceToOrigin() < toCheck.getRadius() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Accessor method for p1, returns beginning point of the Laser.
     * @return is the beginning point of the Laser.
     */
    public Point getP1()
    {
        return p1;
    }
    
    /**
     * Accessor method for p2, returns ending point of the Laser.
     * @return is the ending point of the Laser.
     */
    public Point getP2()
    {
        return p2;
    }
    
    /**
     * Accessor method for the length, returns the length of the Laser.
     * @return is the length of the Laser.
     */
    public double getLength()
    {
        return length;
    }
    
    /**
     * Accessor method for the slope, returns the slope of the Laser.
     * @return is the slope of the Laser.
     */
    public double getSlope()
    {
        return slope;
    }
    
    /**
     * Increments timeAlive by a specified specified amount.
     * @param increase is the specified amunt of incrementation.
     */
    public void incrementTimeAlive( double increase )
    {
        timeAlive = timeAlive + increase;
    }
    
    /**
     * Increments timeToLive by a specified specified amount.
     * @param increase is the specified amunt of incrementation.
     */
    public static void incrementTimeToLive( double increase )
    {
        timeToLive = timeToLive + increase;
    }
    
    /**
     * Determines whether a Laser is alive or not by comparing timeToLive and timeAlive. Returns a boolean expression 
     * indicating it.
     * @return is the boolean, which indicates whether the Laser is alive or not.
     */
    public boolean isAlive()
    {
        return timeToLive > timeAlive;
    }
}