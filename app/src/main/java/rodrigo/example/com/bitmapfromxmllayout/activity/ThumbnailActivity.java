package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rodrigo.example.com.bitmapfromxmllayout.Globals;
import rodrigo.example.com.bitmapfromxmllayout.R;
import rodrigo.example.com.bitmapfromxmllayout.utils.BitmapUtils;
import rodrigo.example.com.bitmapfromxmllayout.utils.PermissionUtils;

public class ThumbnailActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    /**
     * Permissions.
     */

    private static final String[] PERMISSIONS = {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQUEST = 10000;

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Activity.
     */

    private Activity mActivity = ThumbnailActivity.this;

    /**
     * Others.
     */

    private String mPath;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        mPath = "/storage/emulated/0/WhatsApp/Media/WhatsApp Video/VID-20160420-WA0000.mp4";
        checkPermissions();
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
                    Log.i(Globals.LOG_TAG, "onRequestPermissionsResult() -> Permissions true.");
                    BitmapUtils.getVideoThumbnailPath(mActivity, mPath);
                } else {
                    // Permission denied.
                    PermissionUtils.alertAndFinish(mActivity);
                }
                return;
            }
        }
    }

    private void checkPermissions() {
        // Checks the Android version of the device.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean hasReadExternalStoragePermission = PermissionUtils.hasReadExternalStoragePermission(mActivity);
            Boolean hasWriteExternalStorage = PermissionUtils.hasWriteExternalStorage(mActivity);
            if (!hasReadExternalStoragePermission || !hasWriteExternalStorage) {
                Log.i(Globals.LOG_TAG, "checkPermissions() -> Calling requestPermissions().");
                requestPermissions(PERMISSIONS, PERMISSION_REQUEST);
            } else {
                // Permission was granted.
                Log.i(Globals.LOG_TAG, "checkPermissions() -> Permissions true.");
                BitmapUtils.getVideoThumbnailPath(mActivity, mPath);
            }
        } else {
            // Version is below Marshmallow.
            Log.i(Globals.LOG_TAG, "checkPermissions() -> Permissions true.");
            BitmapUtils.getVideoThumbnailPath(mActivity, mPath);
        }
    }
}