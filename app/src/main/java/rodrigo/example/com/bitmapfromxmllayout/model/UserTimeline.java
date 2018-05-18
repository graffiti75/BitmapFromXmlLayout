package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * UserTimeline.java class.
 *
 * @author Rodrigo Cericatto
 * @since Mar 22, 2016
 */
public class UserTimeline {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private String visibility;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public UserTimeline() {}

    public UserTimeline(String visibility) {
        this.visibility = visibility;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "UserTimeline{" +
            "visibility='" + visibility + '\'' +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public String getVisibility() {
        return visibility;
    }
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}