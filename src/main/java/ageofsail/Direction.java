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
    FRONT("forward", 0),
    RIGHT("starboard", 270),
    LEFT("port", 90);
    //BACK("astern", 180);

    private String name;
    private double angle;

    /**
     * Used to create an instance of the enum with a name for the to string and an identifier.
     * @param name The name.
     * @param id The id.
     */
    Direction(final String name, final double angle) {
        this.name = name;
        this.angle = angle;
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
