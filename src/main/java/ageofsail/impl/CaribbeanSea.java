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
    /**
     * The boundings of the map.
     */
    private static double LOWER_LONGITUDE_BOUNDARY = 78;
    /**
     * The boundings of the map.
     */
    private static double UPPER_LONGITUDE_BOUNDARY = 79;
    /**
     * The boundings of the map.
     */
    private static double LOWER_LATITUDE_BOUNDARY = 15;
    /**
     * The boundings of the map.
     */
    private static double UPPER_LATITUDE_BOUNDARY = 16;

    /**
     * The ships sailing the world.
     */
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
    public double getUpperLatitudeBoundary() {
        return UPPER_LATITUDE_BOUNDARY;
    }

    @Override
    public double getLowerLatitudeBoundary() {
        return LOWER_LATITUDE_BOUNDARY;
    }

    @Override
    public double getUpperLongitudeBoundary() {
        return UPPER_LONGITUDE_BOUNDARY;
    }

    @Override
    public double getLowerLongitudeBoundary() {
        return LOWER_LONGITUDE_BOUNDARY;
    }

    @Override
    public void update(double elapsedTime) {
        // Update all ships driving around here.
        for (Ship s: ships) {
            if (!rangeCheck(s.getLongitude(), getLowerLongitudeBoundary(), getUpperLongitudeBoundary())
                    || !rangeCheck(s.getLatitude(), getLowerLatitudeBoundary(), getUpperLatitudeBoundary())) {
                s.leaveMap();
            }
            s.update(elapsedTime);
        }
    }

    private boolean rangeCheck(double value, double lower, double upper) {
        return lower < value && value < upper;
    }
}
