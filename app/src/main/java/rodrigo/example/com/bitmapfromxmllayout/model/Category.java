package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * Category.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 1, 2016
 */
public class Category {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Integer id;
    private String name;
    private String status;
    private String color;
    private String created_at;
    private String updated_at;
    private String type;
    private String icon_path;
    private Integer results;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public Category() {}

    public Category(Integer id, String name, String status, String color, String created_at,
                    String updated_at, String type, String icon_path, Integer results) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.color = color;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.type = type;
        this.icon_path = icon_path;
        this.results = results;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", status='" + status + '\'' +
            ", color='" + color + '\'' +
            ", created_at='" + created_at + '\'' +
            ", updated_at='" + updated_at + '\'' +
            ", type='" + type + '\'' +
            ", icon_path='" + icon_path + '\'' +
            ", results=" + results +
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

    public String getCreatedAt() {
        return created_at;
    }
    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }
    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
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

    public Integer getResults() {
        return results;
    }
    public void setResults(Integer results) {
        this.results = results;
    }
}