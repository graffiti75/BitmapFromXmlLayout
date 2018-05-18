package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rodrigo.example.com.bitmapfromxmllayout.R;
import rodrigo.example.com.bitmapfromxmllayout.utils.FloatingLayoutUtils;

public class AutoCompleteActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Context.
     */

    private Activity mActivity = AutoCompleteActivity.this;

    /**
     * Layout.
     */

    private RelativeLayout mFloatingRelativeLayout;

    private TextView mTitleTextView;
    private EditText mTitleEditText;

    private TextView mCategoryTextView;
    private TextView mSelectCategoryTextView;

    private TextView mPlaceTextView;
    private TextView mSelectPlaceTextView;

    private TextView mDescriptionTextView;
    private EditText mDescriptionEditText;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_text);

        setLayout();
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void setLayout() {
        mFloatingRelativeLayout = (RelativeLayout)findViewById(R.id.id_activity_auto_complete__relative_layout);

        mTitleTextView = (TextView)findViewById(R.id.id_activity_post_text__title_text_view);
        mTitleEditText = (EditText)findViewById(R.id.id_activity_post_text__title_edit_text);

        mCategoryTextView = (TextView)findViewById(R.id.id_activity_post_text__category_text_view);
        mSelectCategoryTextView = (TextView)findViewById(R.id.id_activity_post_text__select_category_text_view);

        mPlaceTextView = (TextView)findViewById(R.id.id_activity_post_text__place_text_view);
        mSelectPlaceTextView = (TextView)findViewById(R.id.id_activity_post_text__select_place_text_view);

        mDescriptionTextView = (TextView)findViewById(R.id.id_activity_post_text__description_text_view);
        mDescriptionEditText = (EditText)findViewById(R.id.id_activity_post_text__description_edit_text);

        FloatingLayoutUtils.setFields(new Object[] {
            mTitleTextView, mTitleEditText, mCategoryTextView, mSelectCategoryTextView,
            mPlaceTextView, mSelectPlaceTextView, mDescriptionTextView, mDescriptionEditText,
            mFloatingRelativeLayout });

        mTitleEditText.addTextChangedListener(FloatingLayoutUtils.setTextWatcher(mActivity, mTitleEditText, true));
        mDescriptionEditText.addTextChangedListener(FloatingLayoutUtils.setTextWatcher(mActivity, mDescriptionEditText, false));
    }
}