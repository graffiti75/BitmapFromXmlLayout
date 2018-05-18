package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * Interest.java class.
 *
 * @author Rodrigo Cericatto
 * @since Jan 20, 2016
 */
public class Interest {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Integer id;
    private String name;
    private String status;
    private String color;
    private String type;
    private String icon_path;
    private String slug;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public Interest() {}

    public Interest(Integer id, String name, String status, String color, String type, String icon_path, String slug) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.color = color;
        this.type = type;
        this.icon_path = icon_path;
        this.slug = slug;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "Interest{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", status='" + status + '\'' +
            ", color='" + color + '\'' +
            ", type='" + type + '\'' +
            ", icon_path='" + icon_path + '\'' +
            ", slug='" + slug + '\'' +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconPath() {
        return icon_path;
    }

    public void setIconPath(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}