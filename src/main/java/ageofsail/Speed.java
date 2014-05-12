package ageofsail;

/**
 * The different speeds a ship can have.
 *
 * @author Michael Fürst
 * @version 1.0
 * @since 2014-05-12
 */
public enum Speed {
    /**
     * The ship is driving at half speed.
     */
    HALF("half", 0.5),
    /**
     * The ship is driving at full speed.
     */
    FULL("full", 1.0),
    /**
     * The ship is stopped.
     */
    HAUL("haul", 0.0);

    private String name;
    private double modifier;

    /**
     * Used to create an instance of the enum with a name for the to string.
     * @param name The speed name.
     */
    Speed(final String name, final double modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    /**
     * Get the speed modifier according to the type of speed.
     * @return The modifier of the speed.
     */
    public double getModifier() {
        return modifier;
    }


    @Override
    public String toString() {
        return name;
    }
}
