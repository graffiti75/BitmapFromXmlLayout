package rodrigo.example.com.bitmapfromxmllayout.model;

import java.util.List;

/**
 * Filters.java class.
 *
 * @author Rodrigo Cericatto
 * @since Apr 1, 2016
 */
public class Filters {

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private List<Category> categories;

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    public Filters() {}

    public Filters(List<Category> categories) {
        this.categories = categories;
    }

    //----------------------------------------------
    // To String
    //----------------------------------------------

    @Override
    public String toString() {
        return "Filters{" +
            "categories=" + categories +
            '}';
    }

    //----------------------------------------------
    // Getters and Setters
    //----------------------------------------------

    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}