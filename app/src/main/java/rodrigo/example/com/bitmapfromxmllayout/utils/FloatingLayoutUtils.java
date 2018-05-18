package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rodrigo.example.com.bitmapfromxmllayout.R;

/**
 * FloatingLayoutUtils.java.
 *
 * @author Rodrigo Cericatto
 * @since May 1, 2016
 */
public class FloatingLayoutUtils {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Controllers.
     */

    private static String[] sLanguages = new String[] { "Android ", "Java", "iOS", "SQL", "JDBC" };
    private static TextView[] sTextViews = new TextView[sLanguages.length];

    private static Integer sScreenWidth;
    private static Integer sDinamicX;

    /**
     * Layout.
     */

    private static RelativeLayout sFloatingRelativeLayout;

    private static TextView sTitleTextView;
    private static EditText sTitleEditText;

    private static TextView sCategoryTextView;
    private static TextView sSelectCategoryTextView;

    private static TextView sPlaceTextView;
    private static TextView sSelectPlaceTextView;

    private static TextView sDescriptionTextView;
    private static EditText sDescriptionEditText;

    //--------------------------------------------------
    // Floating Layout Methods
    //--------------------------------------------------

    private static Integer getDefaultMarginTop(Activity activity) {
        Integer statusBarHeight = getStatusBarHeight(activity);

        Toolbar toolbar = (Toolbar)activity.findViewById(R.id.id_toolbar);
        Integer toolbarHeight = toolbar.getMeasuredHeight();

        Integer titleTextViewHeight = sTitleTextView.getMeasuredHeight();
        Integer titleEditTextHeight = sTitleEditText.getMeasuredHeight();
        Integer categoryTextViewHeight = sCategoryTextView.getMeasuredHeight();
        Integer selectCategoryTextViewHeight = sSelectCategoryTextView.getMeasuredHeight();
        Integer placeTextViewHeight = sPlaceTextView.getMeasuredHeight();
        Integer selectPlaceTextViewHeight = sSelectPlaceTextView.getMeasuredHeight();
        Integer descriptionTextViewHeight = sDescriptionTextView.getMeasuredHeight();
        Integer fieldsHeight = titleTextViewHeight + titleEditTextHeight + categoryTextViewHeight +
            selectCategoryTextViewHeight + placeTextViewHeight + selectPlaceTextViewHeight +
            descriptionTextViewHeight;

        Integer defaultMarginTop = statusBarHeight + toolbarHeight + fieldsHeight;
        return defaultMarginTop;
    }

    private static void getMeasures(Activity activity, Boolean isTitle) {
        if (isTitle) {
            int pos = sTitleEditText.getSelectionStart();
            Layout layout = sTitleEditText.getLayout();
            sDinamicX = (int)layout.getPrimaryHorizontal(pos);
        } else {
            int pos = sDescriptionEditText.getSelectionStart();
            Layout layout = sDescriptionEditText.getLayout();
            sDinamicX = (int)layout.getPrimaryHorizontal(pos);
        }

        sScreenWidth = Utils.getScreenWidth(activity);
    }

    private static void restoreLayouts() {
        for (int i = 0; i < sTextViews.length; i++) {
            sFloatingRelativeLayout.removeView(sTextViews[i]);
        }
        sTextViews = new TextView[sLanguages.length];
    }

    private static Object[] createTextView(Activity activity, EditText parent, Integer layoutWidth,
        Integer layoutHeight, Integer index) {
        final TextView textView = new TextView(activity);
        // Create text view.
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(layoutWidth,
            layoutHeight);
        textView.setBackgroundColor(ContextCompat.getColor(activity, R.color.white));
        textView.setTextColor(ContextCompat.getColor(activity, R.color.grey_800));
        textView.setText(sLanguages[index]);
        textView.setOnClickListener(floatingTextViewListener(parent, textView));

        // Return.
        Object[] object = new Object[] { textView, textViewParams };
        return object;
    }

    private static View.OnClickListener floatingTextViewListener(final EditText parent,
        final TextView textView) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                String currentText = parent.getText().toString();
                String updatedText = currentText + textView.getText().toString();
                parent.setText(updatedText);
                restoreLayouts();
            }
        };
    }

    private static void createFloatingLayout(Activity activity, EditText parent, Integer index, Boolean isTitle) {
        // Variables.
        Integer padding = (int)Utils.dpToPixels(activity, 10);
        Integer layoutWidth = (int)Utils.dpToPixels(activity, 150);
        Integer itemLayoutHeight = (int)Utils.dpToPixels(activity, 40);
        Integer marginLeft = sDinamicX + padding;

        // Creates new text view.
        Object[] textViewValues = createTextView(activity, parent, layoutWidth, itemLayoutHeight, index);
        TextView textView = (TextView)textViewValues[0];
        RelativeLayout.LayoutParams textViewParams = (RelativeLayout.LayoutParams)textViewValues[1];

        // Checks horizontal limit.
        Integer[] integerValues = new Integer[] { layoutWidth, itemLayoutHeight, padding,
            marginLeft, getMarginTop(activity, itemLayoutHeight, isTitle) };
        checkHorizontalLimit(integerValues, index, textView, textViewParams);

        // Adds view.
        sTextViews[index] = textView;
        sFloatingRelativeLayout.addView(sTextViews[index]);
    }

    private static Integer getMarginTop(Activity activity, Integer itemLayoutHeight, Boolean isTitle) {
        Integer marginTop;
        if (isTitle) {
            marginTop = getTitleMarginTop(activity);
        } else {
            marginTop = getDescriptionMarginTop(activity, itemLayoutHeight);
        }
        return marginTop;
    }

    private static Integer getTitleMarginTop(Activity activity) {
        Integer statusBarHeight = getStatusBarHeight(activity);

        Toolbar toolbar = (Toolbar)activity.findViewById(R.id.id_toolbar);
        Integer toolbarHeight = toolbar.getMeasuredHeight();

        Integer titleTextViewHeight = sTitleTextView.getMeasuredHeight();
        Integer titleEditTextHeight = sTitleEditText.getMeasuredHeight();
        Integer fieldsHeight = titleTextViewHeight + titleEditTextHeight;

        Integer titleEditTextSize = (int)sTitleEditText.getTextSize();

        Integer marginTop = statusBarHeight + toolbarHeight + fieldsHeight - titleEditTextSize;
        return marginTop;
    }

    private static Integer getDescriptionMarginTop(Activity activity, Integer itemLayoutHeight) {
        Integer layoutHeight = itemLayoutHeight * sLanguages.length;

        Integer descriptionEditTextSize = (int) sDescriptionEditText.getTextSize();

        Integer marginTop = getDefaultMarginTop(activity) - layoutHeight + (descriptionEditTextSize * 2);
        return marginTop;
    }

    private static void checkHorizontalLimit(Integer[] values, Integer index, TextView textView,
        RelativeLayout.LayoutParams textViewParams) {
        Integer horizontalLimit = sDinamicX + values[0] + values[2];
        Integer horizontalOffset = horizontalLimit - sScreenWidth;
        if (horizontalLimit > sScreenWidth) {
            values[3] = sDinamicX - horizontalOffset;
        }
        textViewParams.setMargins(values[3], values[4] + (values[1] * index), 0, 0);
        textView.setPadding(values[2], values[2], values[2], values[2]);
        textView.setLayoutParams(textViewParams);
    }

    private static Integer getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //--------------------------------------------------
    // EditText Listeners
    //--------------------------------------------------

    public static void setFields(Object[] fields) {
        sTitleTextView = (TextView)fields[0];
        sTitleEditText = (EditText)fields[1];

        sCategoryTextView = (TextView)fields[2];
        sSelectCategoryTextView = (TextView)fields[3];

        sPlaceTextView = (TextView)fields[4];
        sSelectPlaceTextView = (TextView)fields[5];

        sDescriptionTextView = (TextView)fields[6];
        sDescriptionEditText = (EditText)fields[7];

        sFloatingRelativeLayout = (RelativeLayout)fields[8];
    }

    public static TextWatcher setTextWatcher(final Activity activity, final EditText editText,
        final Boolean isTitle) {
        return new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextChangedListener(activity, editText, s, isTitle);
            }

            public void afterTextChanged(Editable s) {}
        };
    }

    private static void onTextChangedListener(Activity activity, EditText editText, CharSequence s,
        Boolean isTitle) {
        if (!StringUtils.isEmpty(s.toString())) {
            restoreLayouts();
            getMeasures(activity, isTitle);
            for (int i = 0; i < sLanguages.length; i++) {
                createFloatingLayout(activity, editText, i, isTitle);
            }
        } else {
            restoreLayouts();
        }
    }
}