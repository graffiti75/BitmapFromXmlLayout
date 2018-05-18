package rodrigo.example.com.bitmapfromxmllayout.model;

/**
 * SearchFilters.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 1, 2016
 */
public class SearchFilters {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private Filters filters;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public SearchFilters() {}

    public SearchFilters(Filters filters) {
        this.filters = filters;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "SearchFilters{" +
            "filters=" + filters +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public Filters getFilters() {
        return filters;
    }
    public void setFilters(Filters filters) {
        this.filters = filters;
    }
}