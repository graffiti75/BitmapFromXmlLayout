package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;

import rodrigo.example.com.bitmapfromxmllayout.utils.BitmapUtils;
import rodrigo.example.com.bitmapfromxmllayout.utils.PermissionUtils;
import rodrigo.example.com.bitmapfromxmllayout.R;

public class MainActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    /**
     * Permissions.
     */

    private static final String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSION_REQUEST = 1212;

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Activity.
     */

    private Activity mActivity = MainActivity.this;

    /**
     * Others.
     */

    private TextCrawler mTextCrawler = new TextCrawler();
    private ImageView mImageView;
    private Bitmap mBitmap;

    /**
     * Permissions.
     */

    private Boolean mHasPermissions = false;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.id_image_view);
        Bitmap bitmap = xmlToBitmap(R.layout.view_item);
        mImageView.setImageBitmap(bitmap);

//        checkPermissions();
//        getLatLong();

//        mTextCrawler.makePreview(mCallback, "http://vnexpress.net/");

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.route_go_down);
//        mImageView.startAnimation(animation);

        /*
        TextView textView = (TextView) findViewById(R.id.id_text_view);
        int color = ContextCompat.getColor(this, R.color.colorPrimary);
        textView.setText("Example of #hashtag text. Hey, here we have another hashtag, the _mode hashtag.");

        char[] additionalSymbols = new char[] { '_' };
        HashTagHelper textHashTagHelper = HashTagHelper.Creator.create(color,
            new HashTagHelper.OnHashTagClickListener() {
                @Override
                public void onHashTagClicked(String hashTag) {
                    Toast.makeText(MainActivity.this, "Clicked.", Toast.LENGTH_SHORT).show();
                }
            },
        additionalSymbols);
        textHashTagHelper.handle(textView);
        */

//        JsonRawUtils.getPostSearchData();

        /*
        mImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                Navigation.animate(MainActivity.this, Navigation.Animation.GO);
            }
        });
        */

        /*
        String text = "test [user=id:5|username:alinekb|fullname:Aline Kol Borges|photo:https://s3.amazonaws.com/dev.static.status.life/5.0053e58cbc907c676990f0f373630424.png]@alinekb[/user] asdfasdfasdf e [user=id:122|username:ozeca|fullname:Ozeias|photo:]@ozeca[/user] asdf [hashtag=id:3]#asdf[/hashtag] teste teste [user=id:8|username:harry_potter|fullname:Harry Potter|photo:]@harry_potter[/user] pppp [hashtag=id:7]#outra-hash[/hashtag]";
        String[] parts = text.split(" ");
        for (int i = 0; i < parts.length; i++) {
            Mention mention = MentionUtils.getMention(text);
        }

        String mentionString = MentionUtils.getMentionString(text);
        Log.i("Test", "Mention string is " + mentionString + ".");
        */

        /*
        String title = "test [user=id:5|username:alinekb|fullname:Aline Kol Borges|photo:https://s3.amazonaws.com/dev.static.status.life/5.0053e58cbc907c676990f0f373630424.png]@alinekb[/user] asdfasdfasdf e [user=id:122|username:ozeca|fullname:Ozeias|photo:]@ozeca[/user] asdf [hashtag=id:3]#asdf[/hashtag] teste teste [user=id:8|username:harry_potter|fullname:Harry Potter|photo:]@harry_potter[/user] pppp [hashtag=id:7]#outra-hash[/hashtag]";
        TextView textView = (TextView)findViewById(R.id.id_text_view);
        MentionUtils.mentionUser(mActivity, title, textView);
        */

        String path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Video/VID-20160420-WA0000.mp4";
        BitmapUtils.getVideoThumbnailPath(mActivity, path);
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private Bitmap xmlToBitmap(int layoutId) {
        View view = LayoutInflater.from(this).inflate(layoutId, null);

        TextView textView = (TextView) view.findViewById(R.id.id_text_view);
        textView.setTextColor(Color.parseColor("#0000FF"));

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    /*
    private void getLatLong() {
        if (mHasPermissions) {
            // Mandatory check (Android Studio forces me to check it).
            int hasPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            int permissionGranted = PackageManager.PERMISSION_GRANTED;
            int checkSelfPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (hasPermission != permissionGranted && checkSelfPermission != permissionGranted) {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    Log.i("Test", "Lat is " + latitude + ", and Long is " + longitude + ".");
                }
            } else {
                PermissionUtils.alertAndFinish(mActivity);
            }
        }
    }
    */

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSION_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //  gps functionality
        }
    }

    private void marshmallowGPSPremissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_LOCATION);
        } else {
            //   gps functions.
        }
    }
    */

    private void getLatLong() {
        // Checks permissions.
        int permissionGranted = PackageManager.PERMISSION_GRANTED;
        int hasAccessFineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasAccessCoarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasAccessFineLocation != permissionGranted && hasAccessCoarseLocation != permissionGranted) {
            if (!mHasPermissions) {
                checkPermissions();
            } else {
                // Gets lat and long.
                Log.i("Test", "getLatLong() -> Checking location.");
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    Log.i("Test", "Lat is " + latitude + ", and Long is " + longitude + ".");
                }
            }
        } else {
            // Gets lat and long.
            Log.i("Test", "getLatLong() -> Checking location.");
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Log.i("Test", "Lat is " + latitude + ", and Long is " + longitude + ".");
            }
        }
    }

    //--------------------------------------------------
    // Permissions
    //--------------------------------------------------

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted.
                    mHasPermissions = true;
                    Log.i("Test", "onRequestPermissionsResult() -> Permissions true.");
                    getLatLong();
                } else {
                    // Permission denied, boo! Disable the functionality that depends on this permission.
                    PermissionUtils.alertAndFinish(mActivity);
                }
                return;
            }
        }
    }

    private void checkPermissions() {
        // Checks the Android version of the device.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean hasAccessCoarseLocation = PermissionUtils.hasAccessCoarseLocation(mActivity);
            Boolean hasAccessFineLocation = PermissionUtils.hasAccessFineLocation(mActivity);
            if (!hasAccessCoarseLocation || !hasAccessFineLocation) {
                Log.i("Test", "checkPermissions() -> Calling requestPermissions().");
                requestPermissions(PERMISSIONS, PERMISSION_REQUEST);
            } else {
                // Permission was granted.
                mHasPermissions = true;
                Log.i("Test", "checkPermissions() -> Permissions true.");
                getLatLong();
            }
        } else {
            // Version is below Marshmallow.
            mHasPermissions = true;
            Log.i("Test", "checkPermissions() -> Permissions true.");
            getLatLong();
        }
    }

    //--------------------------------------------------
    // LinkPreviewCallback
    //--------------------------------------------------

    /**
     * Callback to update your view. Totally customizable.
     * onPre() will be called before the crawling. onPos() after.
     * You can customize this to update your view.
     */
    private LinkPreviewCallback mCallback = new LinkPreviewCallback() {
        @Override
        public void onPre() {}

        @Override
        public void onPos(final SourceContent sourceContent, boolean isNull) {
            if (isNull || sourceContent.getFinalUrl().equals("")) {
                Toast.makeText(MainActivity.this, "Url nula.", Toast.LENGTH_SHORT).show();
            } else {
                String title = sourceContent.getTitle();
                String url = sourceContent.getCannonicalUrl();
                String description = sourceContent.getDescription();
                String text = "Title is " + title + ", Url is " + url + ", Description is " + description + ".";
                Log.i("Test", text);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

                if (sourceContent.getImages().size() > 0) {
                    Integer size = sourceContent.getImages().size();
                    if (size > 1) {
                        Toast.makeText(MainActivity.this, "This link has " + size + " images.", Toast.LENGTH_SHORT).show();
                    }
                    UrlImageViewHelper.setUrlDrawable(mImageView, sourceContent.getImages().get(0), new UrlImageViewCallback() {
                        @Override
                        public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
                            if (loadedBitmap != null) {
                                mBitmap = loadedBitmap;
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "No images.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

}