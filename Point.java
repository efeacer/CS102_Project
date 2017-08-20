import java.io.Serializable;

/**
 * This class represents a Point on the cartesian plane. It involves methods to control the Point and access relevant
 * information.
 * @author BROJECT
 * @version 1.0
 */
public class Point implements Serializable
{
    //Properties
    private double x;
    private double y;
    
    /**
     * The default constructor of the Point class. It produces a random Point on the cartesian plane.
     */
    public Point()
    {
        x = ( int ) ( Math.random() * Main.getFrameWidth() );    
        y = ( int ) ( Math.random() * Main.getFrameHeight() );
    }
    
    /**
     * The constructor of the Point class. It produces a Point on the cartesian plane with the specified coordinates.
     * @param x is the specified x coordinate.
     * @param y is the specified y coordinate.
     */
    public Point( double x, double y )
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the location of the Point to the specified coordinates.
     * @param x is the specified x coordinate.
     * @param y is the specified y coordinate.
     */
    public void setLocation( double x, double y )
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Translates the Point by the given amounts. In other words, moves the Point by a specified amount in the x axis 
     * and the y axis.
     * @param x is the specified amount of translation in the x axis.
     * @param y is the specified amount of translation in the y axis.
     */
    public void translate( double x, double y )
    {
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    /**
     * Checks whether a point is the same with another, returns a relevant boolean.
     * @param other is the point to compare.
     * @return is the relevant boolean.
     */
    public boolean equals( Point other )
    {
        return this.x == other.x &&  this.y == other.y;
    }
    
    /**
     * Multiplies the coordinates of the Point by a specified scalar.
     * @param scalar is the specified scalar
     */
    public void scalarProduct( double scalar )
    {
        this.x = this.x * scalar;
        this.y = this.y * scalar;
    }
    
    /**
     * Multiplies the coordinates of the Point by a specified scalar and returns the Point.
     * @param scalar is the specified scalar
     * @return is the computed point.
     */
    public Point scalar( double scalar )
    {
        return new Point( this.x * scalar, this.y * scalar );
    }
    
    /**
     * Accessesor method for the x coordinate, returns the x coordinate of the Point.
     * @return x is the x coordinate of the Point.
     */
    public double getX()
    {
        return x;
    }
    
    /**
     * Accessesor method for the y coordinate, returns the y coordinate of the Point.
     * @return y is the y coordinate of the Point.
     */
    public double getY()
    {
        return y;
    }
    
    /**
     * Computes and returns the distance between the Point with this reference and another specified Point.
     * @param other is the specified Point that will be used when computing the distance.
     * @return is the distance between the Point with this reference and another specified Point.
     */
    public double getDistance( Point other )
    {
        return Math.sqrt( ( x - other.getX() ) * ( x - other.getX() ) + ( y - other.getY() ) * ( y - other.getY() ) );
    }
    
    /**
     * Returns the dot product of this point with another.
     * @param other is the other point in the dot product operation.
     * @return is the computed dot product.
     */
    public double dotProduct( Point other )
    {
        return x * other.x + y * other.y;
    }
    
    /**
     * Returns the vectoral sum of two points
     * @param other is the other point in the sum operation.
     * @return is the sum of the points.
     */
    public Point sum( Point other )
    {
        return new Point( x + other.x, y + other.y );
    }
    
    /**
     * Returns the vectoral difference of two points
     * @param other is the other point in the difference operation.
     * @return is the difference of the points.
     */
    public Point dif( Point other )
    {
        return new Point( x - other.x, y - other.y );
    }
    
    /**
     * Computes and returns the distance between the Point and the origin.
     * @return is the distance between the Point and the origin.
     */
    public double getDistanceToOrigin( )
    {
        return Math.sqrt( x * x + y * y );
    }  
}