import javax.swing.*;

/**
 * Controls the leaderboards in the Main class using static methods called from elsewhere.
 * @author BROJECT
 * @version 1.0
 */
public class LeaderboardControl
{
    /**
     * Ends the game, opening dialog boxes for leaderboards or playing again, depending
     * on how the player scored
     * @param toCheck the player to be checked
     * @return 0 if the player wants to play again
     */
    public static int endGame(Player toCheck)
    {
        if( Main.saveGame.getLeaderboard().canAdd(toCheck) ) addToLeaderboard(toCheck);
        
        return JOptionPane.showConfirmDialog( Main.getMainFrame(), "Game Over! Play again?\nScore: " +
                                             Main.saveGame.getPlayer().getScore(), "ESCAPE ~ Game over", 0 );
    }
    
    /**
     * If the player is eligible to enter the leaderboard, enter them
     * @param toEnter the player to be entered
     */
    public static void addToLeaderboard(Player toEnter)
    {
        String name = JOptionPane.showInputDialog( "You entered the leaderboard! Your score:" +
                                                  Main.saveGame.getPlayer().getScore() + "\n Your name:" );
        toEnter.setName( name );
        if( name != null ) Main.saveGame.getLeaderboard().addPlayer( toEnter );
        Main.saveGame.saveGame();
    }
}

