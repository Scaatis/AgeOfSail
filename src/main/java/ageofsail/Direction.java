package ageofsail;

/**
 * Directions.
 *
 * Can be used for cannons and stuff like that.
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-12
 */
public enum Direction {
    FRONT(0, "front", 0),
    RIGHT(1, "starboard", 90),
    LEFT(2, "port", 270),
    BACK(3, "back", 180);

    private String name;
    private int id;
    private double angle;

    /**
     * Used to create an instance of the enum with a name for the to string and an identifier.
     * @param name The name.
     * @param id The id.
     */
    Direction(final int id, final String name, final double angle) {
        this.name = name;
        this.id = id;
        this.angle = angle;
    }

    /**
     * Get the id of the direction.
     * @return The id of the direction.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the angle of the direction.
     * @return The angle in degree.
     */
    public double getAngle() {
        return angle;
    }

    @Override
    public String toString() {
        return name;
    }
}
