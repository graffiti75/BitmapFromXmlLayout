package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * Mention.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 7, 2016
 */
public class Mention {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Long userId;
    private String userFullname;
    private String userLogin;
    private Long hashtagId;
    private String hashtagTitle;
    private Integer firstIndex;
    private Integer lastIndex;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public Mention() {}

    public Mention(Long userId, String userFullname, String userLogin, Long hashtagId,
                   String hashtagTitle, Integer firstIndex, Integer lastIndex) {
        this.userId = userId;
        this.userFullname = userFullname;
        this.userLogin = userLogin;
        this.hashtagId = hashtagId;
        this.hashtagTitle = hashtagTitle;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "Mention{" +
                "userId=" + userId +
                ", userFullname='" + userFullname + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", hashtagId=" + hashtagId +
                ", hashtagTitle='" + hashtagTitle + '\'' +
                ", firstIndex=" + firstIndex +
                ", lastIndex=" + lastIndex +
                '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }
    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getHashtagId() {
        return hashtagId;
    }
    public void setHashtagId(Long hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getHashtagTitle() {
        return hashtagTitle;
    }
    public void setHashtagTitle(String hashtagTitle) {
        this.hashtagTitle = hashtagTitle;
    }

    public Integer getFirstIndex() {
        return firstIndex;
    }
    public void setFirstIndex(Integer firstIndex) {
        this.firstIndex = firstIndex;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }
    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }
}