package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * PostComment.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 1, 2016
 */
public class PostComment {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Integer id;
    private Integer parent_id;
    private Long user_id;
    private String text;
    private String status;
    private String created_at;
    private String updated_at;
    private Long post_id;
    private Integer likes_total;
    private User author;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public PostComment() {}

    public PostComment(Integer id, Integer parent_id, Long user_id, String text, String status,
                       String created_at, String updated_at, Long post_id, Integer likes_total, User author) {
        this.id = id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.text = text;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.post_id = post_id;
        this.likes_total = likes_total;
        this.author = author;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "PostComment{" +
            "id=" + id +
            ", parent_id=" + parent_id +
            ", user_id=" + user_id +
            ", text='" + text + '\'' +
            ", status='" + status + '\'' +
            ", created_at='" + created_at + '\'' +
            ", updated_at='" + updated_at + '\'' +
            ", post_id=" + post_id +
            ", likes_total=" + likes_total +
            ", author=" + author +
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

    public Integer getParentId() {
        return parent_id;
    }
    public void setParentId(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Long getUserId() {
        return user_id;
    }
    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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

    public Long getPostId() {
        return post_id;
    }
    public void setPostId(Long post_id) {
        this.post_id = post_id;
    }

    public Integer getLikesTotal() {
        return likes_total;
    }
    public void setLikesTotal(Integer likes_total) {
        this.likes_total = likes_total;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
}