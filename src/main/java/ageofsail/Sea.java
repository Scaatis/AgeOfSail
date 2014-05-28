package ageofsail;

import java.awt.Dimension;

import ageofsail.engine.Scene;

/**
 * An Ocean
 *
 * @author Michael Fürst, Felix Schneider
 * @version 1.0
 * @since 2014-05-12
 */
public class Sea extends Scene {
    
    public static final double WIND_SPEED = 20;
    
    private Vector2D wind;
    
    public Sea() {
        super(new Dimension(1200, 1200));
        wind = new Vector2D.Polar(0, WIND_SPEED);
    }
    
    /**
     * Get the direction of the wind.
     *
     * Direction is measured in radians with 0 being east. (Right)
     */
    public double getWindDirection() {
        return wind.getDirection();
    }

    /**
     * Get the speed of the wind. This applies as the global base speed to all ships
     */
    public double getWindSpeed() {
        return wind.getMagnitude();
    }
    
    public Vector2D getWind() {
        return new Vector2D.Polar(getWindDirection(), getWindSpeed());
    }
}
