package ageofsail;

import java.awt.Point;

import ageofsail.engine.DummyResource;
import ageofsail.engine.GameObject;

public abstract class Ship extends GameObject {
    /**
     * Acceleration of the ship.
     */
    public static final double ACCELERATION    = 1.0;

    /**
     * The cooldown of the cannons.
     */
    public static final double CANNON_COOLDOWN = 1.0;

    private final int          id;
    private int                health          = 0;
    private int                loot            = 0;
    private Vector2D           speed;
    private double[]           gunCooldowns;
    private Sea                world;
    private SailAmount         sailAmount;

    public Ship(int id, Sea world, int health, int loot) {
        super(world, new DummyResource(), new Point()); // FIXME: spawn position here.
        this.id = id;
        this.world = world;
        this.health = health;
        this.loot = loot;
        sailAmount = SailAmount.FULL;
        speed = new Vector2D.Polar(0, 0);
        gunCooldowns = new double[Direction.values().length];
        for (Direction direction : Direction.values()) {
            gunCooldowns[direction.ordinal()] = 0;
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public void update(final double delta) {
        // Update the speed of the ship.alth is ok.
        double windMod = windPhysics();
        // Full speed at full health, half speed at no health
        double healthMod = 0.5 * getHealth() / getMaxHealth() + 0.5;
        double sailMod = sailAmount.getModifier();
        double targetSpeed = world.getWindSpeed() * windMod * healthMod * sailMod;
        if (Math.abs(targetSpeed - speed.getMagnitude()) > 1e4) {
            double diff = speed.getMagnitude() - targetSpeed;
            if (diff < 0) { // we are slower than we should be
                speed = new Vector2D.Polar(speed.getDirection(), speed.getMagnitude() + Math.min(ACCELERATION * delta, diff));
            }
        }

        // Reload guns
        for (Direction direction : Direction.values()) {
            if (gunCooldowns[direction.ordinal()] > 0) {
                gunCooldowns[direction.ordinal()] -= delta;
            }
        }

        // Now finaly move the ship.
        setPosition(speed.scale(delta).applyTo(getPosition()));
        
        // FIXME: Handle going to port
    }

    private double windPhysics() {
        assert world.getWindDirection() >= 0 && world.getWindDirection() < 2 * Math.PI;

        // Calculate in what direction we are to the wind.
        double diff = world.getWind().angleBetween(speed);
        if (diff > Math.PI) {
            diff -= 2 * Math.PI;
        }
        diff = Math.abs(diff);
        assert diff >= 0.0 && diff <= Math.PI;

        // Now modify our speed.
        double res;
        if (diff < 45.0) {
            res = 0;
        } else if (diff < 65) {
            res = 0.4;
        } else if (diff < 115) {
            res = 0.6;
        } else if (diff < 160) {
            res = 1.0;
        } else {
            res = 0.8;
        }
        return res;
    }

    public boolean fire(final Direction direction) {
        if (gunCooldowns[direction.ordinal()] <= 0) {
            gunCooldowns[direction.ordinal()] = CANNON_COOLDOWN;
            return true;
        }
        return false;
    }

    public double getHeading() {
        return speed.getDirection();
    }

    public void setSailAmount(final SailAmount speed) {
        sailAmount = speed;
    }

    public SailAmount getSailAmount() {
        return sailAmount;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }
    
    public abstract int getMaxHealth();

    public void damage(int damage) {
        health -= damage;
        destroy();
        // FIXME: sinking ship sprite
    }

    public int getLoot() {
        return loot;
    }
    
    public abstract void goToPort();

    protected Sea getWorld() {
        return world;
    }

    protected void setLoot(int loot) {
        this.loot = loot;
    }

    protected void setSpeed(Vector2D speed) {
        this.speed = speed;
    }

    protected Vector2D getSpeed() {
        return speed;
    }
}
