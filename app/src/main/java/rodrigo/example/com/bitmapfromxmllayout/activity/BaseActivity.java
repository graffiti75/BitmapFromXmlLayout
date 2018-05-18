package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import rodrigo.example.com.bitmapfromxmllayout.R;
import rodrigo.example.com.bitmapfromxmllayout.utils.ColorUtils;

/**
 * LoginActivity.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 3, 2016
 */
public class BaseActivity extends FontActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Toolbar mToolbar;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        updateColor(R.color.red_500);
    }

    //--------------------------------------------------
    // Menu
    //--------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public void initToolbar(int colorId) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setToolbar(toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, colorId)));
        }
    }

    public void updateColor(int colorId) {
        ColorUtils.changeStatusBar(this, getWindow(), colorId);
        initToolbar(colorId);
    }

    public void initToolbar(String color) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setToolbar(toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
        }
    }

    public void updateColor(String color) {
        ColorUtils.changeStatusBar(getWindow(), color);
        initToolbar(color);
    }

    public void setToolbarTitle(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setToolbar(toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    public void setToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}