package ageofsail.impl;

import ageofsail.Ship;
import ageofsail.World;

import java.util.ArrayList;

/**
 * The caribbean sea.
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-12
 */
public class CaribbeanSea implements World {
    private ArrayList<Ship> ships = new ArrayList<>();

    @Override
    public double getWindDirection() {
        return 0;
    }

    @Override
    public double getWindSpeed() {
        return 0.2;
    }

    @Override
    public void update(double elapsedTime) {
        // Update all ships driving around here.
        for (Ship s: ships) {
            s.update(elapsedTime);
        }
    }
}
