/**
 * This class represents the LaserKiller powerUp.
 * @author BROJECT
 * @version 1.0
 */
public class LaserKiller extends PowerUp
{    
    /**
     * The apply method of the LaserKiller, gets the game instance as a parameter and clears every laser in the game.
     * @param game is the instance of the game.
     */
    public void apply( GamePanel game)
    {
        game.getLasers().clear();
    }
}