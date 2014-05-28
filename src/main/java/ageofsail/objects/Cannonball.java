package ageofsail.objects;

import java.awt.geom.Point2D;

import ageofsail.Ship;
import ageofsail.Vector2D;
import ageofsail.engine.DummyResource;
import ageofsail.engine.GameObject;
import ageofsail.engine.Scene;

public class Cannonball extends GameObject {

    /**
     * How long a cannonball lives, in seconds
     */
    public static final double LIFETIME = 2;

    private Vector2D           speed;
    private double             age;

    public Cannonball(Scene scene, Point2D position, Vector2D speed) {
        super(scene, new DummyResource(), position);
        this.speed = speed;
        age = 0;
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
