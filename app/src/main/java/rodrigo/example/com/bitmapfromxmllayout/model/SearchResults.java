package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * SearchResults.java class.
 *
 * @author Rodrigo Cericatto
 * @since Mar 31, 2016
 */
public class SearchResults {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Integer total;
    private Integer general_listed_total;
    private Integer posts_total;
    private Integer profiles_total;
    private Integer posts_listed_total;
    private Integer profiles_listed_total;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public SearchResults() {}

    public SearchResults(Integer total, Integer general_listed_total, Integer posts_total,
                         Integer profiles_total, Integer posts_listed_total, Integer profiles_listed_total) {
        this.total = total;
        this.general_listed_total = general_listed_total;
        this.posts_total = posts_total;
        this.profiles_total = profiles_total;
        this.posts_listed_total = posts_listed_total;
        this.profiles_listed_total = profiles_listed_total;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "SearchResults{" +
                "total=" + total +
                ", general_listed_total=" + general_listed_total +
                ", posts_total=" + posts_total +
                ", profiles_total=" + profiles_total +
                ", posts_listed_total=" + posts_listed_total +
                ", profiles_listed_total=" + profiles_listed_total +
                '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getGeneralListedTotal() {
        return general_listed_total;
    }

    public void setGeneralListedTotal(Integer general_listed_total) {
        this.general_listed_total = general_listed_total;
    }

    public Integer getPostsTotal() {
        return posts_total;
    }

    public void setPostsTotal(Integer posts_total) {
        this.posts_total = posts_total;
    }

    public Integer getProfilesTotal() {
        return profiles_total;
    }

    public void setProfilesTotal(Integer profiles_total) {
        this.profiles_total = profiles_total;
    }

    public Integer getPostsListedTotal() {
        return posts_listed_total;
    }

    public void setPostsListedTotal(Integer posts_listed_total) {
        this.posts_listed_total = posts_listed_total;
    }

    public Integer getProfilesListedTotal() {
        return profiles_listed_total;
    }

    public void setProfilesListedTotal(Integer profiles_listed_total) {
        this.profiles_listed_total = profiles_listed_total;
    }
}