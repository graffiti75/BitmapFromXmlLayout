package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * UserSettings.java class.
 *
 * @author Rodrigo Cericatto
 * @since Mar 22, 2016
 */
public class UserSettings {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private String favorite_color;
    private UserTimeline timeline;
    private UserTagging tagging;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public UserSettings() {}

    public UserSettings(String favorite_color, UserTimeline timeline, UserTagging tagging) {
        this.favorite_color = favorite_color;
        this.timeline = timeline;
        this.tagging = tagging;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "UserSettings{" +
            "favorite_color='" + favorite_color + '\'' +
            ", timeline=" + timeline +
            ", tagging=" + tagging +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public String getFavoriteColor() {
        return favorite_color;
    }
    public void setFavoriteColor(String favorite_color) {
        this.favorite_color = favorite_color;
    }

    public UserTimeline getTimeline() {
        return timeline;
    }
    public void setTimeline(UserTimeline timeline) {
        this.timeline = timeline;
    }

    public UserTagging getTagging() {
        return tagging;
    }
    public void setTagging(UserTagging tagging) {
        this.tagging = tagging;
    }
}