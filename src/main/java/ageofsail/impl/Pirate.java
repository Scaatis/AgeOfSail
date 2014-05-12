package ageofsail.impl;

import java.net.InetAddress;

import ageofsail.Player;
import ageofsail.Ship;
import ageofsail.Title;

public class Pirate implements Player {

    private String name;
    private Title  title;
    private Ship   ship;
    private int    score;

    public Pirate() {
        name = "unnamed";
        title = Title.NONE;
        ship = null;
        score = 0;
    }

    @Override
    public InetAddress getIP() {
        // FIXME
        return null;
    }

    @Override
    public String getName() {
        return name + title.text();
    }

    @Override
    public void setName(String name) {
        this.name = name;
        // FIXME Add copycat title
    }

    @Override
    public Ship getShip() {
        return ship;
    }

    @Override
    public void setShip(Ship ship) {
        if (this.ship != null) {
            throw new IllegalStateException("A player's ship must not change while he still has one");
        }
        this.ship = ship;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void addScore(int score) {
        this.score += score;
    }

    public void grantTitle(Title title) {
        if (this.title.compareTo(title) < 0) {
            this.title = title;
        }
    }
    
    public void clearTitle() {
        title = Title.NONE;
    }

}
