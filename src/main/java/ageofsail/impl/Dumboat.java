package ageofsail.impl;

import ageofsail.SailAmount;
import ageofsail.World;

/**
 * A dumboat implementation.
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-28
 */
public class Dumboat extends PirateShip {
    /**
     * The initial health of the boat.
     */
    private static int SPAWN_HEALTH = 20;

    /**
     * A new dumboat.
     *
     * @param id The id of the dumboat.
     * @param world The world it sails in.
     */
    public Dumboat(int id, World world) {
        super(id, world, SPAWN_HEALTH);
        spawn();
    }

    @Override
    synchronized public void update(final double elapsedTime) {
        if (isDead()) {
            spawn();
        }
        // Sail downwind.
        setDesiredHeading(world.getWindDirection() + 180);
        // Make it easy to catch them...
        setSailAmount(SailAmount.HALF);

        // TODO shoot back...

        super.update(elapsedTime);
    }

    @Override
    public void leaveMap() {
        spawn();
    }

    /**
     * Spawn at the edges of the map that are into the wind.
     */
    private void spawn() {
        double dirLon = Math.cos(Math.toRadians(world.getWindDirection() + 90.0));
        double dirLat = Math.sin(Math.toRadians(world.getWindDirection() + 90.0));

        // Determine at which edges of the screen spawning is clever.
        double lon = world.getUpperLongitudeBoundary();
        double lat = world.getUpperLatitudeBoundary();
        if (dirLon < 0) {
            lon = world.getUpperLongitudeBoundary();
        }
        if (dirLat < 0) {
            lat = world.getLowerLatitudeBoundary();
        }

        // Give up one edge of the screen and interpolate at the other edge.
        if (Math.random() < 0.5) {
            lat = Math.random() * (world.getUpperLatitudeBoundary()-world.getLowerLatitudeBoundary()) + world.getLowerLatitudeBoundary();
        } else {
            lon = Math.random() * (world.getUpperLongitudeBoundary()-world.getLowerLongitudeBoundary()) + world.getLowerLongitudeBoundary();
        }

        // Cheat our direction, so we cannot leave the map.
        direction = (world.getWindDirection() + 180) % 360;

        // Finally spawn at that position.
        spawn(lat, lon);
    }
}
