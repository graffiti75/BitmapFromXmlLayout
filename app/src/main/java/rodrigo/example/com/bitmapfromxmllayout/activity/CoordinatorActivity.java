package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import rodrigo.example.com.bitmapfromxmllayout.R;

public class CoordinatorActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Activity mActivity = CoordinatorActivity.this;
    private RecyclerView mRecyclerView;
    private CoordinatorAdapter mAdapter;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Urso");
        loadBackdrop();

        setRecyclerView();
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("http://www.infoescola.com/wp-content/uploads/2008/04/urso.jpg").centerCrop().into(imageView);
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.id_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CoordinatorAdapter(mActivity);
        mRecyclerView.setAdapter(mAdapter);
    }
}