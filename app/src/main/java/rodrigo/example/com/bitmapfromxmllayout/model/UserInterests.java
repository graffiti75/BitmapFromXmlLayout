package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * UserInterests.java class.
 *
 * @author Rodrigo Cericatto
 * @since Mar 22, 2016
 */
public class UserInterests {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Long id;
    private Long user_id;
    private Long category_id;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public UserInterests() {}

    public UserInterests(Long id, Long user_id, Long category_id) {
        this.id = id;
        this.user_id = user_id;
        this.category_id = category_id;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "UserInterests{" +
            "id=" + id +
            ", user_id=" + user_id +
            ", category_id=" + category_id +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return user_id;
    }
    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCategoryId() {
        return category_id;
    }
    public void setCategoryId(Long category_id) {
        this.category_id = category_id;
    }
}