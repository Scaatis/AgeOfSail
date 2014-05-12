package ageofsail;

/**
 * Lists all titles that players can have. Note that since enums are inherently
 * ordered, the titles are declared by preference from lowest to highest.
 * 
 * Add more!
 * 
 * @author Felix Schneider
 * @version 1.0
 */
public enum Title {
    NONE(""),
    COPYCAT(" the Copycat"), // picking a name that is already taken
    UNIMAGINATIVE(" the Unimaginative"), // yelling generic taunts
    DESTROYER(" the Destroyer"), // sinking another player
    LANDLUBBER(" the Landlubber"); // firing cannons with non-nautical directions
    
    private final String text;
    
    Title(String text) {
        this.text = text;
    }
    public String text() { return text; }
}
