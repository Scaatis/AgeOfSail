package ageofsail;

import java.net.InetAddress;

public interface Player {
    public InetAddress getIP();
    
    /**
     * Gets this player's name (including any additions to the name)
     * @return The player's full name
     */
    public String getName();
    
    /**
     * Set this player's name. Note that getName may not return the same String
     * passed here, as additions to the name may happen. 
     */
    public void setName(String name);
    
    /**
     * @return This player's ship, if he currently has one, null otherwise.
     */
    public Ship getShip();
    public void setShip(Ship ship);
    
    public int getScore();
    
    /**
     * Add to this player's score
     * @param score The amount to add
     */
    public void addScore(int score);
}
