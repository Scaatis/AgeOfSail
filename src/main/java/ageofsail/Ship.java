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
     * Get the id of a ship.
     * @return The id of the ship.
     */
    public int getId();

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
     * @return Whether fired successfully or not.
     */
    public boolean shoot(final Direction direction);

    /**
     * Set the desired head direction of the ship.
     *
     * @param angle The angle where to head. (in degree anti-clockwise)
     */
    public void setHeadDirection(final double angle);

    /**
     * Get the actual heading direction of the ship.
     * @return The direction the ship is really heading. (in degree anti-clockwise)
     */
    public double getActualHeading();

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

    /**
     * Check if the ship is dead.
     * @return Weather the ship is dead or not.
     */
    public boolean isDead();

    /**
     * Get the health of a ship.
     * @return The health of a ship.
     */
    public int getHealth();

    /**
     * Damage a ship.
     * @param damage The damage to deal.
     */
    public void damage(int damage);
}
