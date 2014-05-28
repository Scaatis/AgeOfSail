package ageofsail.objects;

import java.awt.geom.Point2D;

import ageofsail.Direction;
import ageofsail.Sea;
import ageofsail.Ship;
import ageofsail.Vector2D;
import ageofsail.Vector2D.Polar;

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
        double dir = getSpeed().getDirection();
        if (direction == Direction.LEFT) {
            dir += Math.PI / 2;
            Vector2D offset = new Vector2D.Polar(getSpeed().getDirection(), 1.5);
            // FIXME: Die Parameter hier:
            Point2D start = new Vector2D.Polar(getSpeed().getDirection() + Math.toRadians(135), 5).applyTo(getPosition());
            for (int i = 0; i < 10; i++) {
                getScene().spawnObject(new Cannonball(start, dir, this));
                start = offset.applyTo(start);
            }
            return true;
        } else if (direction == Direction.RIGHT) {
            dir -= Math.PI / 2;
            Vector2D offset = new Vector2D.Polar(getSpeed().getDirection(), 1.5);
            // FIXME: Die Parameter hier:
            Point2D start = new Vector2D.Polar(getSpeed().getDirection() - Math.toRadians(135), 5).applyTo(getPosition());
            for (int i = 0; i < 10; i++) {
                getScene().spawnObject(new Cannonball(start, dir, this));
                start = offset.applyTo(start);
            }
            return true;
        } else if (direction == Direction.FRONT) {
            Vector2D offset = new Vector2D.Polar(getSpeed().getDirection() + Math.PI / 2, 1.5);
            // FIXME: Die Parameter hier:
            Point2D start = new Vector2D.Polar(getSpeed().getDirection() + Math.toRadians(15), 8).applyTo(getPosition());
            for (int i = 0; i < 3; i++) {
                getScene().spawnObject(new Cannonball(start, dir, this));
                start = offset.applyTo(start);
            }
            return true;
        }
        return false;
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
