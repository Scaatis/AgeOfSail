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
    HALF("half"),
    /**
     * The ship is driving at full speed.
     */
    FULL("full"),
    /**
     * The ship is stopped.
     */
    HAUL("haul");

    private String name;

    /**
     * Used to create an instance of the enum with a name for the to string.
     * @param name The speed name.
     */
    Speed(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
