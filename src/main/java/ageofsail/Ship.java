package ageofsail;

/**
 * A ship.
 *
 * A ship has several basic operations it can do.
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-12
 */
public interface Ship {

    /**
     * Updates the ship.
     * Ideally this is called once every frame,
     * however the elapsed time gives the posibility
     * to vary with simulation speed.
     * @param elapsedTime The time elapsed since the last call. (Can also be passed a constant value of 1.0/FPS)
     */
    public void update(final double elapsedTime);

    /**
     * Reload the cannon in the given direction.
     *
     * @param direction The direction.
     */
    public void reload(final Direction direction);

    /**
     * Shoot in the given direction.
     * @param direction The direction.
     */
    public void shoot(final Direction direction);

    /**
     * Set the desired head direction of the ship.
     *
     * @param angle The angle where to head in degree.
     */
    public void setHeadDirection(final double angle);

    /**
     * Set the speed of the ship.
     * @param speed The speed of the ship.
     */
    public void setSpeed(final Speed speed);

    /**
     * Get the speed of the ship.
     * @return The speed of the ship.
     */
    public Speed getSpeed();

    /**
     * The latitude of the ship.
     *
     * Distance to the equator.
     *
     * @return The latitude.
     */
    public double getLatitude();

    /**
     * The longitude of the ship.
     *
     * Distance to the zero meridian.
     *
     * @return The longitude.
     */
    public double getLongitude();
}
