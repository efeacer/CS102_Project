import java.io.Serializable;

/**
 * This class represents the Ball, which the user controls in the game. It involves methods to move the Ball and access
 * relevant information.
 * @author BROJECT
 * @version 1.0
 */
public class Ball implements Serializable
{
    //Constants
    private final int PRICE;
    private final int RADIUS;
    private final double MAX_SPEED;
    
    //Properties
    private boolean isOwned;
    private boolean touchable;
    private Point location;
    private Point velocity;
    private double ballFriction;
    
    /**
     * The constructor of the Ball class; takes the price, radius and maximum speed as parameters. Initializes the 
     * other properties.
     * @param price is the specified price of the Ball.
     * @param radius is the specified radius of the Ball.
     * @param maxSpeed is the specified maximum spped of the Ball.
     */
    public Ball( int price, int radius, double maxSpeed )
    {
        PRICE = price;
        RADIUS = radius;
        MAX_SPEED = maxSpeed;
        ballFriction = 0.1;
        isOwned = false;
        touchable = true;
        location = new Point( Main.getFrameWidth() / 2, Main.getFrameHeight() / 2 ); 
        velocity = new Point( 0, 0 );
    }
    
    /**
     * The copy constructor of the Ball, it takes the Ball to be copied as a parameter.
     * @param other is the Ball to be copied.
     */
    public Ball( Ball other )
    {
        PRICE = other.PRICE;
        RADIUS = other.RADIUS;
        MAX_SPEED = other.MAX_SPEED;
        isOwned = false;
        location = other.getLocation();
        velocity = other.getVelocity();
    }
    
    /**
     * This method creates a copy of another Ball with this reference. It returns the copy.
     * @return is the copied ball. 
     */
    public Ball createDeepCopy()
    {
        if ( this instanceof SunBall)
        {
            Ball other = new SunBall();
            other.setTouchable( this.touchable  );
            return other;
        }
        if ( this instanceof EarthBall)
        {
            
            Ball other = new EarthBall();
            other.setTouchable( this.touchable  );
            return other;
        }
        if ( this instanceof MoonBall)
        {
            
            Ball other = new MoonBall();
            other.setTouchable( this.touchable  );
            return other;
        }
        throw
            new Error( " Ball not found");
        
    }
    /**
     * Sets the location of the Ball to a specified Point.
     * @param position is the specified Point which will be the location of the Ball.
     */
    public void setPosition( Point position )
    {
        if ( position != null ) //in case of a Null-Pointer
        {
            location = position;
        }
    }
    
    /**
     * Translates the location of the Ball with respect to the specified translation vector.
     * @param vector is the specified translation vector.
     */
    public void translate( Point vector )
    {
        location.translate( vector.getX(), vector.getY() );
    }
    
    /**
     * Accelerates the ball with respect to a specified acceleration vector.
     * @param vector is the specified acceleration vector.
     */
    public void accelerate( Point vector )
    {
        if ( new Point( velocity.getX() + vector.getX(), velocity.getY() + vector.getY() ).getDistanceToOrigin() <=
            MAX_SPEED )
        {
            velocity.translate( vector.getX(), vector.getY() );
        }
    }
    
    /**
     * Sets the velocity of the ball to a specified velocity vector.
     * @param vector is the specified velocity vector.
     */
    public void setVelocity( Point vector )
    {
        if ( vector.getDistanceToOrigin() <= MAX_SPEED && vector != null ) //in case of a Null-Pointer
        {
            velocity = vector;
        }
    }
    
    /**
     * Accessor method for the location, returns the location of the Ball.
     * @return location is the current location of the Ball.
     */
    public Point getLocation()
    {
        return location;
    }
    
    /**
     * Accessor method for the velocity, returns the velocity of the Ball.
     * @return velocity is the current velocity of the Ball.
     */
    public Point getVelocity()
    {
        return velocity;
    }
    
    /**
     * multiplies the current velocity of the ball wit a given scalar
     */
    public void multiplyVelocity( double scalar)
    {
        velocity.scalarProduct( scalar);
    }
    
    /**
     * Accessor method for the radius, returns the radius of the Ball.
     * @return radius is the radius of the Ball.
     */
    public int getRadius()
    {
        return RADIUS;
    }
    
    /**
     * Accessor method for the touchable property, returns the touchable state of the Ball.
     * @return touchable is the touchable state of the Ball.
     */
    public boolean isTouchable()
    {
        return touchable;
    }
    
    /**
     * Accessor method for the price of the Ball.
     * @return PRICE is the price of the Ball.
     */
    public int getPrice()
    {
        return PRICE;
    }
    
    /**
     * Sets the touchable state of the Ball.
     * @param toSet is the boolean expression which will be set as the touchable property.
     */
    public void setTouchable( boolean toSet )
    {
        touchable = toSet;
    }
    
    /**
     * Sets the owned state of the Ball.
     * @param toSet is the boolean expression which will be set as the isOwned property.
     */
    public void setOwned( boolean toSet )
    {
        isOwned = toSet;
    }
    
    /**
     * Accessor method for the isOwned property of the Ball.
     * @return is the isOwned property of the Ball.
     */
    public boolean isOwned()
    {
        return isOwned;
    }
    
    /**
     * Accessor method for the speed, computes and returns the speed of the Ball.
     * @return is the current speed of the Ball.
     */
    public double getSpeed()
    {
        return velocity.getDistanceToOrigin();
    }
    
    /**
     * Changes the acceleration of the ball by a given amount in a given direction.
     * @param direction is the direction in which the ball will accelerate.
     * @param amount is the amount of acceleration that will be added.
     */
    public void accelerate( char direction, double amount )
    {
        if ( velocity.getY() >=  -6 )
        {
            if ( direction == 'u' )
            {
                velocity.translate( 0, - amount );
            }
        }
        if ( velocity.getY() <= 6 )
        {
            if ( direction == 'd' )
            {
                velocity.translate( 0, amount );
            }
        }
        if ( velocity.getX() <= 6 )
        {
            if ( direction == 'r' )
            {
                velocity.translate( amount, 0 );
            }
        }
        if ( velocity.getX() >= -6 )
        {
            if ( direction == 'l' )
            {
                velocity.translate( - amount, 0 );
            }
        }
    }
    
    /**
     * Moves the ball with respect to its current velocity
     */
    public void move( ) 
    {
        if ( location.getX() +  velocity.getX() <= Main.getFrameWidth() - RADIUS &&
            location.getX() +  velocity.getX() >= RADIUS &&
            location.getY() + velocity.getY() <= Main.getFrameHeight() - 2 * RADIUS &&
            location.getY() +  velocity.getY() >= RADIUS )
        {
            location.translate( velocity.getX(), velocity.getY() ); 
        }
        else
        {
            if( location.getX() + velocity.getX() < RADIUS )
            {
                velocity.setLocation( 0, velocity.getY());
                location.translate( 0, velocity.getY() );
                location.setLocation( RADIUS, location.getY());
            }
            else if( location.getX() + velocity.getX() > Main.getFrameWidth() - RADIUS)
            {
                velocity.setLocation( 0, velocity.getY());
                location.translate( 0, velocity.getY() );
                location.setLocation( Main.getFrameWidth() - RADIUS, location.getY());
            }
            
            if( location.getY() +  velocity.getY() < RADIUS )
            {
                velocity.setLocation( velocity.getX(), 0);
                location.translate( velocity.getX(), 0);
                location.setLocation( location.getX(), RADIUS);
            }
            else if( location.getY() + velocity.getY() > Main.getFrameHeight() - 2 * RADIUS )
            {
                velocity.setLocation( velocity.getX(), 0);
                location.translate( velocity.getX(), 0);
                location.setLocation( location.getX(), Main.getFrameHeight() - 2 * RADIUS);
            }
        }
        
        if ( velocity.getX() < 0 ) 
        {
            velocity.translate( ballFriction, 0 );
        }
        else if ( velocity.getX() > 0 ) 
        {
            velocity.translate( - ballFriction, 0 );
        }
        
        if ( velocity.getY() < 0 ) 
        {
            velocity.translate( 0, ballFriction );
        }
        else if ( velocity.getY() > 0 )
        {
            velocity.translate( 0, - ballFriction );
        }
    }
}