package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rodrigo.example.com.bitmapfromxmllayout.Navigation;
import rodrigo.example.com.bitmapfromxmllayout.R;

public class TestActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Navigation.animate(this, Navigation.Animation.BACK);
    }
}