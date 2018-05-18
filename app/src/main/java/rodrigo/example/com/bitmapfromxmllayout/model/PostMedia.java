package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * PostMedia.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 1, 2016
 */
public class PostMedia {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private String url;
    private String type;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public PostMedia() {}

    public PostMedia(String url, String type) {
        this.url = url;
        this.type = type;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "PostMedia{" +
            "url='" + url + '\'' +
            ", type='" + type + '\'' +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}