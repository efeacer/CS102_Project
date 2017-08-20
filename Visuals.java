//necessary imports
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is responsible for drawing the items of the game.
 * @author BROJECT
 * @version 1.0
 */
public class Visuals
{
    //properties
    private BufferedImage sunImage;
    private BufferedImage moonImage;
    private BufferedImage earthImage;
    private BufferedImage multiply;
    private BufferedImage slowTime;
    private BufferedImage shield;
    private BufferedImage laserKiller;
    
    /**
     * The consyructor of the Visuals object, reads the image files.
     */
    public Visuals()
    {
        try
        {
            sunImage = ImageIO.read( new File( "images/sunImage.png" ) );
            moonImage = ImageIO.read( new File( "images/moonImage.png" ) );
            earthImage = ImageIO.read( new File( "images/earthImage.png" ) );
            multiply = ImageIO.read( new File( "images/multiply.png" ) );
            slowTime = ImageIO.read( new File( "images/timeslow.png" ) );
            shield = ImageIO.read( new File( "images/shield.png" ) );
            laserKiller = ImageIO.read( new File( "images/laserkiller.png" ) );
        }
        catch( IOException exception ){}
    }
    
    /**
     * Represents the ball visually, gets the Ball that will be drawn as a parameter. Also, takes the Graphics object
     * that will draw the Ball as a parameter.
     * @param toDraw is the Ball that will be drawn.
     * @param g is the Graphics object that will draw the Ball.
     */
    public void drawBall( Ball toDraw, Graphics g )
    {
        if ( toDraw instanceof SunBall )
        {
            g.drawImage( sunImage,( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof MoonBall )
        {
            g.drawImage( moonImage,( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof EarthBall )
        {
            g.drawImage( earthImage,( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
    }
    
    /**
     * Represents the laser visually, get the Laser that will be drawn as a parameter. Also, takes the Graphics object
     * that will draw the Laser as a parameter.
     * @param toDraw is the Laser that will be drawn.
     * @param g is the Graphics object that will draw the Laser.
     */
    public void drawLaser( Laser toDraw, Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setStroke( new BasicStroke( 4 ) );
        g.setColor( Color.RED );
        g.drawLine( ( int ) toDraw.getP1().getX(), ( int ) toDraw.getP1().getY(), ( int ) toDraw.getP2().getX(),
                   ( int ) toDraw.getP2().getY() );
    }
    
    /**
     * Draws the powerup icons.
     * @param g is the graphics object.
     * @param toDraw is the specified powerup
     */
    public void drawPowerUp( PowerUp toDraw, Graphics g )
    {
        if ( toDraw instanceof SlowTime )
        {
            g.drawImage( slowTime, ( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof Multiply )
        {
            g.drawImage( multiply, ( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof LaserKiller )
        {
            g.drawImage( laserKiller, ( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof Shield )
        {
            g.drawImage( shield, ( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
    }
    
    /**
     * Draws the effects of the powerups.
     * @param g is the graphics object.
     * @param toDraw is the specified powerup
     * @param toEffect is the ball that will be affected
     */
    public void drawPowerUpEffect( PowerUp toDraw, Ball toEffect, Graphics g ) // Work in progress
    {
        g.setColor( new Color( 255, 165, 0, 130 ) );
        g.fillOval( ( int ) toEffect.getLocation().getX() - toEffect.getRadius() - 15 , 
                   ( int ) toEffect.getLocation().getY() - toEffect.getRadius() - 15, 
                   2 * ( toEffect.getRadius() + 15 ), 2 * ( toEffect.getRadius() + 15 ) );
    }
}