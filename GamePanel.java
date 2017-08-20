import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;

/**
 * The game panel of escape
 * @author BROJECT
 * @version 1.0
 */
public class GamePanel extends JPanel
{
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    private static final float ACC_AMOUNT = 0.4f;
    private static final float POWER_UP_RARITY = 3f; // a higher number is rarer. min 1
    
    //Properties
    private Image background;
    private BufferedImage backArrow;
    private BufferedImage pauseIcon;
    private BufferedImage resumeIcon;
    private JButton backButton;
    private JButton pauseButton;
    private ArrayList<Ball> balls;
    private ArrayList<Laser> lasers;
    private ArrayList<PowerUp> powerUps;
    private Visuals painter;
    private Timer motionTimer;
    private Timer gameTimer;
    private Timer scoreTimer;
    private int delay;
    private int playAgain;
    private int resume;
    private double score;
    private boolean[] pressedKeyList;
    private Point dirVector;
    private double pace;
    private Shield active;
    
    /**
     * The constructor for this panel, creates and sets images and buttons.
     */
    public GamePanel()
    {
        setLayout( null );
        
        addImages();
        
        addButtons();
        
        //keyboard control declarations
        pressedKeyList = new boolean[]{ false, false, false,false};
        dirVector = new Point( 0, 0);
        
        balls = new ArrayList<Ball>();
        balls.add( Main.saveGame.getPlayer().currentBall() );
        dirVector.setLocation( 2, 2);
        lasers = new ArrayList<Laser>();
        powerUps = new ArrayList<PowerUp>();
        painter = new Visuals();
        pace = 1;
        addKeyListener( new BallListener() );
        motionTimer = new Timer( 16, new MotionListener() );
        //motionTimer.start();
        delay = 1000;
        gameTimer = new Timer( delay, new TimerListener() );
        //gameTimer.start();
        score = 0;
        scoreTimer = new Timer( 100, new ScoreListener() );
        //scoreTimer.start();
        
    }
    
    /**
     * adds images to this panel 
     */
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "images/gameBackground.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
            pauseIcon = ImageIO.read( new File( "images/pauseIcon.png" ) );
            resumeIcon = ImageIO.read( new File( "images/resumeIcon.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    /**
     * creates and adds buttons to this panel
     */
    public void addButtons()
    {
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        pauseButton = new JButton( new ImageIcon( pauseIcon.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        backButton.setBounds( 20, 20, 50, 50 );
        pauseButton.setBounds( BACKGROUND_WIDTH - 70, 20, 50, 50 );
        
        backButton.setBorderPainted( false );
        backButton.setContentAreaFilled( false );
        backButton.setOpaque( false );
        pauseButton.setBorderPainted( false );
        pauseButton.setContentAreaFilled( false );
        pauseButton.setOpaque( false );
        
        backButton.addActionListener( new BackButtonListener() );
        pauseButton.addActionListener( new PauseButtonListener() );
        
        add( backButton );
        add( pauseButton );
    }
    
    /**
     * restarts the game
     */
    public void restartGame()
    {
        balls.clear();
        powerUps.clear();
        balls.add( Main.saveGame.getPlayer().currentBall() );
        dirVector = new Point( 0, 0);
        balls.get( 0 ).setVelocity( new Point( 0, 0 ) );
        balls.get( 0 ).getLocation().setLocation( BACKGROUND_WIDTH / 2, BACKGROUND_HEIGHT / 2 );
        pressedKeyList = new boolean[]{ false, false, false,false};       
        lasers = new ArrayList<Laser>();
        addKeyListener( new BallListener() );
        delay = 1000;
        motionTimer.restart();
        gameTimer.restart();
        score = 0;
        scoreTimer.restart();
        //Main.setPanel( new GamePanel() );
    }
    
    
    /**
     * Paints the components of this panel, gets a Graphics object as a parameter.
     * @param g is the Graphics object.
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        
        g.setFont( new Font( "SansSerif", Font.BOLD, 20 ) );
        g.setColor( Color.WHITE );
        g.drawString( "SCORE: " + score / 100.0, 395, 30 );
        
        for ( Ball toDraw: balls)
        {
            painter.drawBall( toDraw , g );
        }
        for ( Laser toDraw: lasers )
        {
            painter.drawLaser( toDraw, g );
        }
        for ( PowerUp toDraw: powerUps)
        {
            painter.drawPowerUp( toDraw , g );
            //painter.drawPowerUpEffect( new Shield(), balls.get(0), g );
        }
        for ( Ball toEffect: balls)
        {
            if ( active != null && active.isActive() )
            {
                painter.drawPowerUpEffect( active, toEffect, g );
            }
        }
        requestFocusInWindow( true );
    }
    
    /**
     * pauses the game
     */
    public void pause()
    {
        gameTimer.stop();
        motionTimer.stop();
        scoreTimer.stop();
    }
    
    /**
     * resumes the game
     */
    public void resume()
    {
        gameTimer.start();
        motionTimer.start();
        scoreTimer.start();
    }
    
    /**
     * inner class that is responsible for the pause button
     */
    private class PauseButtonListener implements ActionListener
    {
        /**
         * implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event )
        {
            pause();
            pauseButton.setIcon( new ImageIcon( resumeIcon.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
            resume = JOptionPane.showConfirmDialog( GamePanel.this, "Game is paused! Resume?\nCurrent score: " 
                                                       + score / 100.0, "ESCAPE ~ Paused", 0 );
            if ( resume == 0 )
            {
                resume();
                pauseButton.setIcon( new ImageIcon( pauseIcon.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
            }
        }
    }
    
    /**
     * inner class that is responsible for the back button
     */
    private class BackButtonListener implements ActionListener
    {
        /**
         * implemented actionPerformed method
         */
        public void actionPerformed( ActionEvent event )
        {
            pause();
            Main.getStack().show( Main.getCards(), "menu" );
            //pause();
//            Main.setPanel( new MainMenuPanel() );
        }
    }
    
    /**
     * inner class responsible for the balls movement 
     */
    private class BallListener extends KeyAdapter
    {      
        /**
         * implemented key pressed method
         */
        @Override
        public void keyPressed( KeyEvent event )
        {
            if ( event.getKeyCode() == KeyEvent.VK_UP )
            {
                pressedKeyList[0] = true;
            }
            if ( event.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                pressedKeyList[1] = true;
            }
            if ( event.getKeyCode() == KeyEvent.VK_DOWN )
            {
                pressedKeyList[2] = true;
            }
            if ( event.getKeyCode() == KeyEvent.VK_LEFT )
            {
                pressedKeyList[3] = true;
            }
        }
        
        /**
         * implemented key released method
         */
        public void keyReleased( KeyEvent event )
        {
            if ( event.getKeyCode() == KeyEvent.VK_UP )
            {
                pressedKeyList[0] = false;
            }
            if ( event.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                pressedKeyList[1] = false;
            }
            if ( event.getKeyCode() == KeyEvent.VK_DOWN )
            {
                pressedKeyList[2] = false;
            }
            if ( event.getKeyCode() == KeyEvent.VK_LEFT )
            {
                pressedKeyList[3] = false;
            }
        }
    }
    
    /**
     * Inner class responsible for the generation of the components
     */
    private class MotionListener implements ActionListener
    {
        /**
         * overriden actionPerformed
         */
        @Override
        public void actionPerformed( ActionEvent event )
        {
            for ( Laser check : lasers )
            {
                for ( int i = balls.size() - 1; i >= 0; i--)
                {
                    if ( check.isTouched( balls.get(i) ))
                    {
                        balls.remove( i);
                        System.out.println( balls.size());
                        if ( balls.size() == 0)
                        {
                            pause();
                            playAgain = LeaderboardControl.endGame(Main.saveGame.getPlayer());
                            if ( playAgain == 0 )
                            {
                                restartGame();
                            }
                            else
                            {
                                Main.getStack().show( Main.getCards(), "menu" );
                            }
                        }
                    }
                }
            }
            
            for( int i = powerUps.size() - 1; i >= 0; i--)
            {
                if ( powerUps.get(i).isTaken( balls))
                {
                    applyPowerUp( powerUps.get(i));
                    if ( powerUps.get(i) instanceof Shield ) 
                    {
                        active = (Shield)powerUps.get(i);
                        repaint();
                    }
                    powerUps.remove(i);
                }
            }
            updateDirectionVector();
            for ( Ball ball: balls)
            {
                ball.accelerate( dirVector);
                ball.move();
            }
            gameTimer.setDelay( (int)(delay / pace));
            repaint();
        }
    }
    
    /**
     * Inner class for the timer
     */
    private class TimerListener implements ActionListener
    {
        /**
         * implemented action performed
         */
        @Override
        public void actionPerformed( ActionEvent event )
        {
            lasers.add( new Laser() );
            
            // This part creates a random powerUp. Starting from here...
            int ran = (int)(Math.random() * 4 * POWER_UP_RARITY);
            if( ran == 0)
            {
                powerUps.add( new Multiply());
            }
            else if( ran == 1)
            {
                powerUps.add( new SlowTime());
            }
            else if( ran == 2)
            {
                powerUps.add( new Shield());
            }
            else if( ran == 3)
            {
                powerUps.add( new LaserKiller());
            }
            //...to here.
            
            repaint();
            
            if ( delay >= 100 )
            {
                delay = delay - 1;
            }
            for ( int i = lasers.size() - 1; i >= 0; i--)
            {
                lasers.get( i ).incrementTimeAlive( 1 );
                if ( !lasers.get( i ).isAlive() )
                {
                    lasers.remove( i );
                }
            }
            for ( int i = powerUps.size() - 1; i >= 0; i--)
            {
                powerUps.get( i ).incrementTimeAlive( 1 );
                if ( !powerUps.get( i ).isAlive() )
                {
                    powerUps.remove( i );
                }
            }
            Laser.incrementTimeToLive( 0.3 );
        }
    }
    
    /**
     * inner class responsible for the score updates
     */
    private class ScoreListener implements ActionListener
    {
        /**
         * implemented action performed method.
         */
        @Override
        public void actionPerformed( ActionEvent event )
        {
            score = score + 10;
            Main.saveGame.getPlayer().updateScore( score / 100.0 );
            if(score % 50 == 0) Main.saveGame.getPlayer().addMoney(1); //scale the increase in money
            repaint();
        }
    }
    
    /**
     * Updates the direction vector
     */
    private void updateDirectionVector()
    {
        dirVector.setLocation( 0 , 0 );
        
        if( pressedKeyList[0])
        {
            dirVector.translate( 0, -1 );
        }
        if( pressedKeyList[1])
        {
            dirVector.translate( 1, 0);
        }
        if( pressedKeyList[2])
        {
            dirVector.translate( 0, 1 );
        }
        if( pressedKeyList[3])
        {
            dirVector.translate( -1, 0);
        }
        if( dirVector.getX() != 0 && dirVector.getY() != 0)
        {
            dirVector.scalarProduct( 1 / dirVector.getDistanceToOrigin() );
        }
        dirVector.scalarProduct( ACC_AMOUNT * pace);
    }
    
    /**
     * returns the array list of balls
     * @return balls is the list of balls
     */
    public ArrayList<Ball> getBalls()
    {
        return balls;
    }
    
    /**
     * sets the balls
     * @param balls is the balls to set
     */
    public void setBalls( ArrayList<Ball> balls)
    {
        this.balls = balls;
    }
    
    /**
     * adds the balls
     * @param toAdd is the balls to add
     */
    public void addBalls( ArrayList<Ball> toAdd)
    {
        for( Ball ball: toAdd)
        {
            this.balls.add( ball);
        }
    }
    
    /**
     * returns the array list of lasers
     * @return lasers is the list of lasers
     */
    public ArrayList<Laser> getLasers()
    {
        return lasers;
    }
    
    /**
     * returns the powerUps
     * @return powerUps is the list of powerUps
     */
    public ArrayList<PowerUp> getPowerUps()
    {
        return powerUps;
    }
    
    
    /**
     * returns the pace
     * @return pace is the pace of the game
     */
    public double getPace()
    {
        return pace;
    }
    
    
    /**
     * sets the game pace
     */
    public void setPace( double pace)
    {
        this.pace = pace;
    }
    
    /**
     * applies a power up
     */
    private void applyPowerUp(PowerUp pu)
    {
        pu.apply( this);
    }
    
    /**
     * returns the active shield
     * @return active is the current active shield.
     */
    public Shield getActive()
    {
        return active;
    }
}