package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Utils.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 12, 2016
 */
public class Utils {

    //--------------------------------------------------
    // Json Generics Methods
    //--------------------------------------------------

    public static <T extends Object> T fromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static final <T> List<T> getListFromJson(String json) {
        Type type = new TypeToken<List<T>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    public static <T> List<T> getListFromJson(Class<T[]> clazz, String json) {
        T[] jsonToObject = new Gson().fromJson(json, clazz);
        return Arrays.asList(jsonToObject);
    }

    //--------------------------------------------------
    // Screen Methods
    //--------------------------------------------------

    public static float pixelsToDp(Context context, float pixels) {
        return pixels / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPixels(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static final Integer getScreenWidth(Activity context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }

    public static final Integer getScreenHeight(Activity context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        return height;
    }

    //--------------------------------------------------
    // Keyboard Methods
    //--------------------------------------------------

    public static void showKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void enableButtonFocus(View view, Boolean enable) {
        view.setFocusable(enable);
        view.setFocusableInTouchMode(enable);
    }
}