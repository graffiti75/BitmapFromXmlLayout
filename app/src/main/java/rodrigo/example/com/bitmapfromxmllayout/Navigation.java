package rodrigo.example.com.bitmapfromxmllayout;

import android.app.Activity;

/**
 * Navigation.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 8, 2016
 */
public class Navigation {

    //--------------------------------------------------
    // Enum
    //--------------------------------------------------

    public enum Animation {
        GO,
        BACK
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public static void animate(Activity activity, Animation animation) {
        if (animation == Animation.GO) {
            activity.overridePendingTransition(R.anim.open_next, R.anim.close_previous);
        } else {
            activity.finish();
            activity.overridePendingTransition(R.anim.open_previous, R.anim.close_next);
        }
    }
}