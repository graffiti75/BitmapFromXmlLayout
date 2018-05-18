package rodrigo.example.com.bitmapfromxmllayout.model;

import java.util.List;

/**
 * Post.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 1, 2016
 */
public class Post {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Long id;
    private Long user_id;
    private Integer category_id;
    private String title;
    private String description;
    private List<PostMedia> medias;
    private String status;
    private Long popularity;
    private String created_at;
    private String updated_at;
    private String city_name;
    private String state_name;
    private String country_name;
    private String latitude;
    private String longitude;
    private String formatted_address;
    private String result_type;
    private List<PostComment> comments;
    private Integer comments_total;
    private Boolean i_like;
    private Integer likes_total;
    private Interest category;
    private User publisher;
    private Long state_id;
    private Long country_id;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public Post() {}

    public Post(Long id, Long user_id, Integer category_id, String title, String description,
        List<PostMedia> medias, String status, Long popularity, String created_at,
        String updated_at, String city_name, String state_name, String country_name,
        String latitude, String longitude, String formatted_address, String result_type,
        List<PostComment> comments, Integer comments_total, Boolean i_like, Integer likes_total,
        Interest category, User publisher, Long state_id, Long country_id) {
        this.id = id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.title = title;
        this.description = description;
        this.medias = medias;
        this.status = status;
        this.popularity = popularity;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.city_name = city_name;
        this.state_name = state_name;
        this.country_name = country_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.formatted_address = formatted_address;
        this.result_type = result_type;
        this.comments = comments;
        this.comments_total = comments_total;
        this.i_like = i_like;
        this.likes_total = likes_total;
        this.category = category;
        this.publisher = publisher;
        this.state_id = state_id;
        this.country_id = country_id;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", user_id=" + user_id +
            ", category_id=" + category_id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", medias=" + medias +
            ", status='" + status + '\'' +
            ", popularity=" + popularity +
            ", created_at='" + created_at + '\'' +
            ", updated_at='" + updated_at + '\'' +
            ", city_name='" + city_name + '\'' +
            ", state_name='" + state_name + '\'' +
            ", country_name='" + country_name + '\'' +
            ", latitude='" + latitude + '\'' +
            ", longitude='" + longitude + '\'' +
            ", formatted_address='" + formatted_address + '\'' +
            ", result_type='" + result_type + '\'' +
            ", comments=" + comments +
            ", comments_total=" + comments_total +
            ", i_like=" + i_like +
            ", likes_total=" + likes_total +
            ", category=" + category +
            ", publisher=" + publisher +
            ", state_id=" + state_id +
            ", country_id=" + country_id +
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

    public Integer getCategoryId() {
        return category_id;
    }
    public void setCategoryId(Integer category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<PostMedia> getMedias() {
        return medias;
    }
    public void setMedias(List<PostMedia> medias) {
        this.medias = medias;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPopularity() {
        return popularity;
    }
    public void setPopularity(Long popularity) {
        this.popularity = popularity;
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

    public String getCityName() {
        return city_name;
    }
    public void setCityName(String city_name) {
        this.city_name = city_name;
    }

    public String getStateName() {
        return state_name;
    }
    public void setStateName(String state_name) {
        this.state_name = state_name;
    }

    public String getCountryName() {
        return country_name;
    }
    public void setCountryName(String country_name) {
        this.country_name = country_name;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFormattedAddress() {
        return formatted_address;
    }
    public void setFormattedAddress(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getResultType() {
        return result_type;
    }
    public void setResultType(String result_type) {
        this.result_type = result_type;
    }

    public List<PostComment> getComments() {
        return comments;
    }
    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public Integer getCommentsTotal() {
        return comments_total;
    }
    public void setCommentsTotal(Integer comments_total) {
        this.comments_total = comments_total;
    }

    public Boolean getIlike() {
        return i_like;
    }
    public void setIlike(Boolean i_like) {
        this.i_like = i_like;
    }

    public Integer getLikesTotal() {
        return likes_total;
    }
    public void setLikesTotal(Integer likes_total) {
        this.likes_total = likes_total;
    }

    public Interest getCategory() {
        return category;
    }
    public void setCategory(Interest category) {
        this.category = category;
    }

    public User getPublisher() {
        return publisher;
    }
    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public Long getStateId() {
        return state_id;
    }
    public void setStateId(Long state_id) {
        this.state_id = state_id;
    }

    public Long getCountryId() {
        return country_id;
    }
    public void setCountryId(Long country_id) {
        this.country_id = country_id;
    }
}