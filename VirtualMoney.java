import java.io.Serializable;

/**
 * Virtual Money class for Escape game
 * @author BROJECT
 * @version 1.0
 */
public class VirtualMoney implements Serializable
{
    private int amount;
    
    /**
     * Initializes a VirtualMoney with zero money
     */
    public VirtualMoney()
    {
        amount = 0;
    }
    
    /**
     * Add some money to this account
     * @param amount the amount to add as an integer
     */
    public void addMoney(int amount)
    {
        this.amount += amount;
    }
    
    /**
     * Spend money from this account
     * @param amount the amount to subtract
     * @return whether spending it was successful
     */
    public boolean spendMoney(int amount)
    {
        if(this.amount >= amount)
        {
            this.amount -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * Get the amount of money in this wallet
     * @return the amount
     */
    public int getMoney()
    {
        return amount;
    }
    
    
    /**
     * Check whether the given amount of money can be spent
     * @param amount the amount to check
     * @return true if it can be spent
     */
    public boolean canSpend(int amount)
    {
        return this.amount >= amount;
    }
}