package ageofsail.objects;

import java.awt.geom.Point2D;

import ageofsail.Player;
import ageofsail.Ship;
import ageofsail.Vector2D;
import ageofsail.Vector2D.Polar;
import ageofsail.engine.DummyResource;
import ageofsail.engine.GameObject;
import ageofsail.engine.Scene;

public class Cannonball extends GameObject {

    /**
     * How long a cannonball lives, in seconds
     */
    public static final double LIFETIME = 2;
    
    /**
     * How fast a cannonball is
     */
    public static final double SPEED = 180;

    private Vector2D           speed;
    private double             age;
    private Ship               ship;

    public Cannonball(Point2D position, double direction, Ship ship) {
        super(new DummyResource(), position);
        this.speed = new Vector2D.Polar(direction, SPEED);
        age = 0;
        this.ship = ship;
        // FIXME: Smoke
    }

    @Override
    public void update(double delta) {
        setPosition(speed.scale(delta).applyTo(getPosition()));
        // FIXME: collisions
        age += delta;

        if (age >= LIFETIME) {
            // FIXME: splash
            destroy();
        }
    }

    private void collideWith(Ship other) {
        other.damage(1);
        destroy();
        // FIXME: explosion
    }

}
