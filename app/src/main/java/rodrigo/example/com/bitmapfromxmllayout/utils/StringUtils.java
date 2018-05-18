package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * StringUtils.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 20, 2016
 */
public class StringUtils {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    public static final Integer INDEX_NOT_FOUND = -1;

    //--------------------------------------------------
    // String Methods
    //--------------------------------------------------

    /**
     * General.
     */

    public static void setHashtags(Activity context, TextView textView, String text, String key) {
        SpannableString spannable = new SpannableString(text);

        Object[] object = getIndexes(spannable.toString(), key);
        int[] firstIndexes = (int[])object[0];
        int[] lastIndexes = (int[])object[1];
        for (int i = 0; i < firstIndexes.length; i++) {
            spannable.setSpan(getClicableSpan(context, firstIndexes[i], lastIndexes[i] + 1, text),
                firstIndexes[i], lastIndexes[i] + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static ClickableSpan getClicableSpan(final Activity context, int firstIndex, int lastIndex, String text) {
        final String substring = text.substring(firstIndex, lastIndex);
        return new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(context, "Clicked word is '" + substring + "'.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint paint) {
                super.updateDrawState(paint);
                paint.setUnderlineText(false);
                String color = "#FF0000";
                paint.setColor(Color.parseColor(color));
            }
        };
    }

    private static Object[] getIndexes(String text, String key) {
        // Gets the number of occurrences.
        int count = 0;
        for (int i = 0; (i = text.indexOf(key, i + 1)) != -1; ) {
            count++;
        }

        // Find all occurrences of first indexes.
        int[] firstIndexes = new int[count];
        int j = 0;
        for (int i = 0; (i = text.indexOf(key, i + 1)) != -1; ) {
            firstIndexes[j++] = i;
        }

        // Find all occurrences of last indexes.
        int[] lastIndexes = new int[count];
        for (int i = 0; i < firstIndexes.length; i++) {
            lastIndexes[i] = getLastIndexes(firstIndexes, text, i);
        }

        // Return.
        Object[] object = new Object[2];
        object[0] = firstIndexes;
        object[1] = lastIndexes;
        return object;
    }

    private static int getLastIndexes(int[] firstIndexes, String text, int currentIndex) {
        boolean found = false;
        int index = 0;
        for (int i = firstIndexes[currentIndex]; !found; i++) {
            if (text.charAt(i) == ' ' || i == text.length() - 1) {
                found = true;
                index = i - 1;
            }
        }
        return index;
    }

    public static Boolean isEmpty(String text) {
        Boolean result = true;
        Boolean isNull = (text == null);
        if (!isNull) {
            Boolean isZeroLength = (text.length() <= 0);
            Boolean isEmpty = (text.equals(""));
            result = isNull || isZeroLength || isEmpty;
        }
        return result;
    }

    /**
     * Trim.
     */

    public static String trimEdges(String entry) {
        String leading = trimLeadingString(entry);
        String trailing = trimTrailingString(leading);
        return trailing;
    }

    public static String trimLeadingString(String entry) {
        String output = stripStart(entry, " ");
        return output;
    }

    public static String trimTrailingString(String entry) {
        String output = stripEnd(entry, " ");
        return output;
    }

    /**
     * Strip String.
     */

    public static String strip(final String str) {
        return strip(str, null);
    }

    public static String stripStart(final String str, final String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return str.substring(start);
    }

    public static String stripEnd(final String str, final String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    public static String strip(String str, final String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }
}