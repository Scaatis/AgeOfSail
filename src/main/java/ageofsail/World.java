package ageofsail;

/**
 * The world.
 *
 * The world in which all ship drive around.
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-12
 */
public interface World {
    /**
     * Get the direction of the wind.
     *
     * A wind direction outside the range of [0,360) will be interpreted as no wind direction.
     * Ships will drive full speed in all directions.
     *
     * @return The direction of the wind. (in degree distinct from north anti-clockwise)
     */
    public double getWindDirection();

    /**
     * Get the speed of the wind.
     *
     * The speed of the wind modifies the speed of the boats.
     *
     * @return The speed of the wind (in long.lat/second)
     */
    public double getWindSpeed();

    /**
     * Update the world.
     *
     * This should also update all ships inside the world.
     *
     * @param elapsedTime The time elapsed since the last call.
     */
    public void update(final double elapsedTime);
}
