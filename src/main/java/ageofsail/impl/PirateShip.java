package ageofsail.impl;

import ageofsail.Direction;
import ageofsail.Ship;
import ageofsail.Speed;
import ageofsail.World;

/**
 * A pirate ship. Arrrrrrrr....
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-12
 */
public class PirateShip implements Ship{
    private static final int SHOOTING_DIRECTIONS = 0;

    /**
     * The turning speed of a ship.
     */
    private static double TURN_SPEED = 1.0;

    /**
     * Acceleration of the ship.
     */
    private static double ACCELERATION = 1.0;

    /**
     * Limit the max speed a ship can have, if it is more, it dies... :)
     */
    private static double MAX_SPEED = 1.0;

    /**
     * The recoil of the cannons.
     */
    private static double RECOIL = 1.0;

    private final int id;
    private int health = 0;
    private double x;
    private double y;
    private double speed;
    private double direction;
    private double[] reloadTime = new double [SHOOTING_DIRECTIONS];

    private double headDirection;
    private Speed speedLevel;
    private World world;

    public PirateShip(final int id, final World world, final int health, final int latitude, final int longitude) {
        this.id = id;
        this.world = world;
        this.health = health;
        this.x = longitude;
        this.y = latitude;
    }

    @Override
    synchronized public int getId() {
        return id;
    }

    @Override
    synchronized public void update(final double elapsedTime) {

        // Update the direction of the ship.
        if (headDirection != direction) {
            double dif = headDirection - direction;
            dif = dif > 180.0 ? dif - 360.0 : dif;
            dif = dif > TURN_SPEED * elapsedTime ? TURN_SPEED * elapsedTime : dif;
            direction += dif;
            direction = (direction + 360.0) % 360.0;
        }

        // Update the speed of the ship.
        double calcSpeed = applyWindPhysics(speedLevel.getModifier() * world.getWindSpeed(), direction);
        if (calcSpeed != speed) {
            double dif = speed - calcSpeed;
            double mod = world.getWindSpeed();
            dif = dif > ACCELERATION * mod * elapsedTime ? ACCELERATION * mod * elapsedTime : dif;
            dif = dif < -ACCELERATION * mod * elapsedTime ? -ACCELERATION * mod * elapsedTime : dif;
            speed += dif;
        }

        // The ship capsizes if it is too fast.
        if (speed >= MAX_SPEED) {
            health = 0;
        }

        // Reload guns
        for (int i = 0; i < reloadTime.length; i++) {
            reloadTime[i] -= elapsedTime;
            reloadTime[i] = reloadTime[i] > 0 ? reloadTime[i] : 0;
        }

        // Now finaly move that ship.
        x += speed * Math.cos(direction);
        y += speed * Math.sin(direction);
    }

    private double applyWindPhysics(final double speed, final double direction) {
        // Check if the direction is correct, otherwise apply no wind direction.
        if (world.getWindDirection() < 0.0 || world.getWindDirection() >= 360.0) {
            return speed;
        }
        // Calculate in what direction we are to the wind.
        double dif = world.getWindDirection() - direction;
        dif = dif > 180.0 ? dif - 360 : dif;
        dif = Math.abs(dif);

        assert dif >= 0.0 && dif <= 180.0;

        // Now modify our speed.
        double res = speed;
        if (dif < 45.0) {
            res = 0;
        } else if (dif < 65) {
            res *= 0.4;
        } else if (dif < 115) {
            res *= 0.6;
        } else if (dif < 120) {
            res *= 1.0;
        } else {
            res *= 0.8;
        }
        return res;
    }

    @Override
    synchronized public void reload(final Direction direction) {
        // FIXME is this really usefull?
    }

    @Override
    synchronized public boolean shoot(final Direction direction) {
        if (reloadTime[direction.getId()] == 0) {
            reloadTime[direction.getId()] = RECOIL;
            return true;
        }
        return false;
    }

    @Override
    synchronized public void setHeadDirection(final double angle) {
        headDirection = angle;
    }

    @Override
    synchronized public double getActualHeading() {
        return direction;
    }

    @Override
    synchronized public void setSpeed(final Speed speed) {
        speedLevel = speed;
    }

    @Override
    synchronized public Speed getSpeed() {
        return speedLevel;
    }

    @Override
    synchronized public double getLatitude() {
        return y;
    }

    @Override
    synchronized public double getLongitude() {
        return x;
    }

    @Override
    synchronized public boolean isDead() {
        return health <= 0;
    }

    @Override
    synchronized public int getHealth() {
        return health;
    }

    @Override
    synchronized public void damage(int damage) {
        health -= damage;
    }
}
