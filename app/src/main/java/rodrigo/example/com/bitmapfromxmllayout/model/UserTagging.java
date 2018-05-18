package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * UserTagging.java class.
 *
 * @author Rodrigo Cericatto
 * @since Mar 22, 2016
 */
public class UserTagging {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private String by;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public UserTagging() {}

    public UserTagging(String by) {
        this.by = by;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "UserTagging{" +
            "by='" + by + '\'' +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }
}