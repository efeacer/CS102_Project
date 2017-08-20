import java.util.ArrayList;
import java.io.Serializable;

/**
 * Leaderboard class for Escape game
 * @author BROJECT
 * @version 1.0
 */
public class Leaderboard implements Serializable
{
    //Constants
    final int SIZE = 10;
    
    //Properties
    private ArrayList<Player> list;
    
    /**
     * The constructor for the Leaderboard, initializes a list of players.
     */
    public Leaderboard()
    {
        list = new ArrayList<Player>();
    }
    
    /**
     * It adds the player to the leaderboard list with the correct place the user belongs.
     * @param toAdd the player to add
     */
    public void addPlayer( Player toAdd )
    {
        Player toAddCopy = new Player( toAdd );
        System.err.println( "Entered add player subroutine" );
        System.err.println( canAdd( toAddCopy ) );
        
        if( list.isEmpty() )
        {
            list.add( toAddCopy );
            return;
        }
        
        
        if( canAdd( toAdd ) ) //check if there is room for this player to enter the leaderboards
        {
            for ( Player p : list )
            {
                if ( toAdd.getScore() >= p.getScore() )
                {
                    list.add( list.indexOf( p ), toAddCopy );
                    if( list.size() > SIZE ) list.remove( SIZE );
                    return;
                }
            }
            if( list.size() < SIZE) 
            {
                list.add(toAddCopy);
            }
        }
    }
    
    /**
     * Checks if it can add the player to leaderboard.
     * @param toAdd the player to add
     * @return true if the player can be added
     */
    public boolean canAdd( Player toAdd )
    {
        return list.isEmpty() || ( list.size() < SIZE ) || ( toAdd.getScore() > list.get( list.size() - 1 ).getScore() );
    }
    
    /**
     * Returns an ArrayList<String of players on the leaderboard
     * @return output is the players on the list
     */
    public ArrayList<String> printMethod()
    {
        ArrayList<String> output = new ArrayList<String>();
        for( Player i: list )
        {
            output.add( i.getName() + "    " + i.getScore() );
        }
        return output;
    }
    
    /**
     * Returns a string representation of this Leaderboard
     * @return a string
     */
    public String toString()
    {
        StringBuffer output = new StringBuffer( "" );
        for( String str : printMethod() ) output.append( str ).append( "\n" );
        return output.toString();
    }
    
    /**
     * Returns a string representation of just the names
     * @return the names with proper LF
     */
    public String namesToString()
    {
        StringBuffer output = new StringBuffer( "" );
        for( Player p : list ) output.append( p.getName() ).append("\n");
        return output.toString();
    }
    
    /**
     * Returns a string representation of just the scores, with rounding
     * @return the scores with proper LF, corresponding to the players
     */
    public String scoresToString()
    {
        StringBuffer output = new StringBuffer( "" );
        for( Player p : list )
        {
            output.append( (p.getScore() ) ).append( "\n" );
        }
        return output.toString();
    }
}

