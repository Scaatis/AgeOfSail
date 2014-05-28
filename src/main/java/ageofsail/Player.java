package ageofsail;

import java.net.InetAddress;

import ageofsail.objects.PirateShip;

public class Player {

    private String name;
    private InetAddress address;
    private Title  title;
    private PirateShip   ship;
    private int    score;

    public Player(InetAddress address) {
        this.address = address;
        name = "An unnamed Sailor";
        title = Title.NONE;
        ship = null;
        score = 0;
    }


    public InetAddress getIP() {
        return address;
    }

    /**
     * Gets this player's name (including any additions to the name)
     * @return The player's full name
     */
    public String getName() {
        return name + title.text();
    }

    /**
     * Set this player's name. Note that getName may not return the same String
     * passed here, as additions to the name may happen. 
     */
    public void setName(String name) {
        this.name = name;
        // FIXME Add copycat title
    }

    public Ship getShip() {
        return ship;
    }

    /**
     * @return This player's ship, if he currently has one, null otherwise.
     */
    public void setShip(PirateShip ship) {
        if (this.ship != null) {
            throw new IllegalStateException("A player's ship must not change while he still has one");
        }
        this.ship = ship;
    }

    public int getScore() {
        return score;
    }

    /**
     * Add to this player's score
     * @param score The amount to add
     */
    public void addScore(int score) {
        this.score += score;
    }
    
    /**
     * Grant a title to a player, if he does not already hold a higher one
     * @param title
     */
    public void grantTitle(Title title) {
        if (this.title.compareTo(title) < 0) {
            this.title = title;
        }
    }
    
    /**
     * Revoke a player's title
     */
    public void clearTitle() {
        title = Title.NONE;
    }

}
