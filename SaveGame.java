import java.io.*;

/**
 * The database of the game.
 * @author BROJECT
 * @version 2017-05-07
 */
public class SaveGame
{   
    //Constants
    private final String PFILE_NAME = "txt/player.txt";
    private final String LFILE_NAME = "txt/leaderboard.txt";
    
    //Properties
    private Player player;
    private Leaderboard leaderboard;
    
    /**
     * Default constructor for the SaveGame. Tries to recall game data from memory,
     * but if the game data is not in memory the relevant objects' default constructors
     * are called.
     */
    public SaveGame() 
    {
        try {
            FileInputStream pInput = new FileInputStream(PFILE_NAME);
            FileInputStream lInput = new FileInputStream(LFILE_NAME);
            
            ObjectInputStream pInStr = new ObjectInputStream(pInput);
            ObjectInputStream lInStr = new ObjectInputStream(lInput);
            
            player = (Player) pInStr.readObject();
            leaderboard = (Leaderboard) lInStr.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            player = new Player();
            leaderboard = new Leaderboard();
            try {
                if (!new File(PFILE_NAME).exists()) new File(PFILE_NAME).createNewFile();
                if (!new File(LFILE_NAME).exists()) new File(LFILE_NAME).createNewFile();
            }
            catch(Exception ex){}
        }
    }
    
    /**
     * Saves the game if it can.
     * @return true if it can
     */
    public boolean saveGame()
    {
        try
        {
            ObjectOutputStream pOutput = new ObjectOutputStream(new FileOutputStream(PFILE_NAME));
            ObjectOutputStream sOutput = new ObjectOutputStream(new FileOutputStream(LFILE_NAME));
            
            pOutput.writeObject(player);
            sOutput.writeObject(leaderboard);
            
            pOutput.close();
            sOutput.close();
            return true;
        }
        catch(Exception e)
        {
            File pFile = new File(PFILE_NAME);
            File lFile = new File(LFILE_NAME);
            try
            {
                pFile.createNewFile(); //attempt to create a new file, maybe there isn't one
                lFile.createNewFile(); //ditto
            }
            catch(IOException ex){ex.printStackTrace();}
            
            e.printStackTrace();
            return false;
        }   
    }
    
    /**
     * Get the player retrieved from the savegame
     * @return the player
     */
    public Player getPlayer() 
    {
        return player;
    }
    
    /**
     * Get the leaderboards retrieved from the savegame
     * @return the leaderboards
     */
    public Leaderboard getLeaderboard() 
    { 
        return leaderboard; 
    }
}