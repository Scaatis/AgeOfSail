package ageofsail.objects;

import ageofsail.Direction;
import ageofsail.Sea;
import ageofsail.Ship;

/**
 * A pirate ship. Arrrrrrrr....
 *
 * @author Michael Fürst, Felix Schneider
 * @version 1.0
 * @since 2014-05-12
 */
public class PirateShip extends Ship {
    /**
     * The turning speed of a ship.
     */
    public static final double TURN_SPEED = 1.0;

    public static final int    MAX_HEALTH = 30;

    private double             desiredHeading;

    public PirateShip(final int id, final Sea world) {
        super(id, world, MAX_HEALTH, 0);
        // FIXME: add spawning mechanics
        desiredHeading = 0;
    }

    @Override
    public void update(final double delta) {
        // Update the direction of the ship.
        if (Math.abs(desiredHeading - getHeading()) > 1e4) {
            double diff = getHeading() - desiredHeading;
            if (diff < 0) {
                setSpeed(getSpeed().rotate(Math.min(-TURN_SPEED * delta, diff)));
            } else {
                setSpeed(getSpeed().rotate(Math.min(TURN_SPEED * delta, diff)));
            }
        }
        super.update(delta);
    }

    @Override
    public boolean fire(final Direction direction) {
        if (!super.fire(direction)) {
            return false;
        }
        // FIXME: Add Cannonballs
        return true;
    }

    public void setDesiredHeading(final double angle) {
        desiredHeading = angle;
    }

    public double getDesiredHeading() {
        return desiredHeading;
    }
    
    public void addLoot(int collected) { // FIXME: This needs to be collect(LootChest chest)
        setLoot(getLoot() + collected); // FIXME: Despawn lootchest
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public void goToPort() {
        // FIXME
    }
}
