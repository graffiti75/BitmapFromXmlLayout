package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import rodrigo.example.com.bitmapfromxmllayout.R;

/**
 * PermissionUtils.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 19, 2016
 */
public class PermissionUtils {

    //--------------------------------------------------
    // Permissions Methods
    //--------------------------------------------------

    public static boolean hasPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (PackageManager.PERMISSION_GRANTED == context.checkSelfPermission(permission));
        }
        return false;
    }

    public static void alertAndFinish(final Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name).setMessage(context.getString(R.string.permissions_denial));

        // Add the buttons.
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static Boolean hasAccessCoarseLocation(Activity context) {
        return (PermissionUtils.hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    public static Boolean hasAccessFineLocation(Activity context) {
        return (PermissionUtils.hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION));
    }

    public static Boolean hasCameraPermission(Activity context) {
        return (PermissionUtils.hasPermission(context, Manifest.permission.CAMERA));
    }

    public static Boolean hasReadExternalStoragePermission(Activity context) {
        return (PermissionUtils.hasPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    public static Boolean hasWriteExternalStorage(Activity activity) {
        return (PermissionUtils.hasPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    public static Boolean hasRecordAudio(Activity activity) {
        return (PermissionUtils.hasPermission(activity, Manifest.permission.RECORD_AUDIO));
    }
}