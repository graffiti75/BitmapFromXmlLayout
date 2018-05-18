package rodrigo.example.com.bitmapfromxmllayout.model;

import java.util.List;

/**
 * User.java class.
 *
 * @author Rodrigo Cericatto
 * @since Mar 22, 2016
 */
public class User {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Long id;
    private String fullname;
    private String username;
    private String email;
    private String photo;
    private String relationship;
    private String profession;
    private String birthdate;
    private String hometown;
    private String lives_in;
    private String type;
    private UserSettings settings;
    private String status;
    private Integer confirmed;
    private String created_at;
    private Long following_total;
    private Long followers_total;
    private Boolean im_following;
    private List<UserInterests> interests;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public User() {}

    public User(Long id, String fullname, String username, String email, String photo,
                String relationship, String profession, String birthdate, String hometown, String lives_in,
                String type, UserSettings settings, String status, Integer confirmed, String created_at,
                Long following_total, Long followers_total, Boolean im_following,
                List<UserInterests> interests) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.relationship = relationship;
        this.profession = profession;
        this.birthdate = birthdate;
        this.hometown = hometown;
        this.lives_in = lives_in;
        this.type = type;
        this.settings = settings;
        this.status = status;
        this.confirmed = confirmed;
        this.created_at = created_at;
        this.following_total = following_total;
        this.followers_total = followers_total;
        this.im_following = im_following;
        this.interests = interests;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", fullname='" + fullname + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", photo='" + photo + '\'' +
            ", relationship='" + relationship + '\'' +
            ", profession='" + profession + '\'' +
            ", birthdate='" + birthdate + '\'' +
            ", hometown='" + hometown + '\'' +
            ", lives_in='" + lives_in + '\'' +
            ", type='" + type + '\'' +
            ", settings=" + settings +
            ", status='" + status + '\'' +
            ", confirmed=" + confirmed +
            ", created_at='" + created_at + '\'' +
            ", following_total=" + following_total +
            ", followers_total=" + followers_total +
            ", im_following=" + im_following +
            ", interests=" + interests +
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

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getHometown() {
        return hometown;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getLivesIn() {
        return lives_in;
    }
    public void setLivesIn(String lives_in) {
        this.lives_in = lives_in;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public UserSettings getSettings() {
        return settings;
    }
    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getConfirmed() {
        return confirmed;
    }
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public String getCreatedAt() {
        return created_at;
    }
    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public Long getFollowingTotal() {
        return following_total;
    }
    public void setFollowingTotal(Long following_total) {
        this.following_total = following_total;
    }

    public Long getFollowersTotal() {
        return followers_total;
    }
    public void setFollowersTotal(Long followers_total) {
        this.followers_total = followers_total;
    }

    public Boolean getImFollowing() {
        return im_following;
    }
    public void setImFollowing(Boolean im_following) {
        this.im_following = im_following;
    }

    public List<UserInterests> getInterests() {
        return interests;
    }
    public void setInterests(List<UserInterests> interests) {
        this.interests = interests;
    }
}