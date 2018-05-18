package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import rodrigo.example.com.bitmapfromxmllayout.R;
import rodrigo.example.com.bitmapfromxmllayout.utils.ColorUtils;
import rodrigo.example.com.bitmapfromxmllayout.utils.Utils;

public class ParallaxActivity extends FontActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private static final Integer DELAY = 100;

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Activity mActivity = ParallaxActivity.this;

    private Handler mHandler = new Handler();

    private Toolbar mToolbar;
    private ImageView mParallax;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        ColorUtils.changeStatusBarTransparent(getWindow());
        initToolbar(R.id.id_toolbar);

        mParallax = (ImageView)findViewById(R.id.id_parallax);
        mParallax.setOnTouchListener(touchListener());
        mHandler.postDelayed(mHandlerChecker, DELAY);
    }

    //--------------------------------------------------
    // Menu
    //--------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer id = menuItem.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.id_menu_info:
                break;
            case R.id.id_menu_share_profile:
                break;
            case R.id.id_menu_report_user:
                break;
        }
        return false;
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void initToolbar(int toolbarId) {
        // Toolbar.
        mToolbar = (Toolbar)findViewById(toolbarId);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    public View.OnTouchListener touchListener() {
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Integer minimumHeight = (int)Utils.dpToPixels(mActivity, 100);
                        Integer maximumHeight = (int)Utils.dpToPixels(mActivity, 320);
                        Integer rawY = (int)event.getRawY();
                        if (rawY > maximumHeight) {
                            rawY = maximumHeight;
                        }
                        if (rawY < minimumHeight) {
                            rawY = minimumHeight;
                        }
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT, rawY);
                        view.setLayoutParams(params);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        };
        return listener;
    }

    //--------------------------------------------------
    // Handler
    //--------------------------------------------------

    private Runnable mHandlerChecker = new Runnable() {
        public void run() {
            Integer height = mParallax.getMeasuredHeight();
            Integer minimumHeight = (int)Utils.dpToPixels(mActivity, 80);
            Integer offset = (int)Utils.dpToPixels(mActivity, 40);
            Integer size = minimumHeight + offset;
            if (height < size) {
                mToolbar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.blue_500));
                mParallax.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.black_transparent_0));
                mParallax.setImageBitmap(null);
            } else {
                mToolbar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.black_transparent_0));
                mParallax.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.orange_500));
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                mParallax.setImageBitmap(bitmap);
            }
            mHandler.removeCallbacks(mHandlerChecker);
            mHandler.postDelayed(mHandlerChecker, DELAY);
        }
    };
}