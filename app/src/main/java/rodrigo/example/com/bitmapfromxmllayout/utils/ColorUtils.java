package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

/**
 * ColorUtils.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 12, 2016
 */
public class ColorUtils {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    public static final String[] DEFAULT_COLORS = {
       "#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#03A9F4",
        "#00BCD4", "#009688", "#4CAF50", "#8BC34A", "#CDDC39", "#FFEB3B", "#FFC107",
        "#FF9800", "#FF5722", "#795548", "#9E9E9E", "#607D8B", "#212121", "#AC9456"
    };

    public static final String[] CLICKED_COLORS = {
        "#EF9A9A", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#90CAF9", "#81D4FA",
        "#80DEEA", "#80CBC4", "#A5D6A7", "#C5E1A5", "#E6EE9C", "#FFF59D", "#FFE082",
        "#FFAB91", "#FFAB91", "#BCAAA4", "#EEEEEE", "#B0BEC5", "#757575", "#FFC107"
    };

    //--------------------------------------------------
    // Color Methods
    //--------------------------------------------------

    public static void changeStatusBar(Context context, Window window, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(context, color));
        }
    }

    public static void changeStatusBar(Window window, String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    public static void changeStatusBarTransparent(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public static String addTransparencyToColor(String color) {
        color = color.replace("#", "");
        color = "#FF" + color;
        return color;
    }

    public static String removeTransparencyFromColor(String color) {
        String newColor = color.toUpperCase();
        if (color.length() == 9) {
            newColor = color.substring(3, 9);
            newColor = "#" + newColor;
        }
        return newColor;
    }

    public static int getColorWithAlpha(int color, float ratio) {
        int alpha = Math.round(Color.alpha(color) * ratio), newColor;
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

    public static String convertIntToColorString(int color) {
//        String hex = String.format("#%06X", 0xFFFFFF & color);
        String hex = Integer.toHexString(color);
        hex = "#" + hex;
        if (hex.length() % 2 == 0) {
            hex = hex.replace("#", "");
            hex = "#0" + hex;
        }
        return hex;
    }

    public static String convertColorResourceToColorString(Activity context, int colorResource) {
        String hex = Integer.toHexString(ContextCompat.getColor(context, colorResource) & 0x00ffffff);
        if (hex.length() == 4) {
            hex = "00" + hex;
        }
        hex = "#" + hex;
        return hex;
    }

    public static Bitmap changeImageColor(Bitmap sourceBitmap, int color) {
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }

    public static String getClickedColor(String normalColor) {
        normalColor = normalColor.toUpperCase();
        normalColor = ColorUtils.removeTransparencyFromColor(normalColor);
        String clickedColor = "";
        Boolean found = false;
        for (int i = 0; (i < DEFAULT_COLORS.length) && (!found); i++) {
            if (normalColor.equals(DEFAULT_COLORS[i])) {
                clickedColor = CLICKED_COLORS[i];
                found = true;
            }
        }
        return clickedColor;
    }
}