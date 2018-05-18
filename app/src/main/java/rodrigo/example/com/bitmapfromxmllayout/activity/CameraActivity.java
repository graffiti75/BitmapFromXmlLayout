package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import rodrigo.example.com.bitmapfromxmllayout.utils.PermissionUtils;
import rodrigo.example.com.bitmapfromxmllayout.R;

public class CameraActivity extends Activity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    /**
     * Permissions.
     */

    private static final String[] PERMISSIONS = { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private static final int PERMISSION_REQUEST = 1212;

    /**
     * Intents.
     */

    public static final Integer TAKE_PHOTO_CODE = 0;

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Contexts.
     */
    private Activity mActivity = CameraActivity.this;

    /**
     * Photo.
     */

    public static Integer sCount = 0;
    private ImageView mImageView;
    private String mFilePath;

    /**
     * Permissions.
     */

    private Boolean mHasPermissions = false;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mImageView = (ImageView)findViewById(R.id.id_image_view);
        checkPermissions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO_CODE) {
                Log.d("CameraDemo", "Pic saved");
                setCameraImage(data);
            }
        }
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void setCamera() {
        // Here, we are making a folder named picFolder to store pics taken by the camera using this application.
        final String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        Log.i("Test", "Directory path is " + dirPath + ".");
        File dir = new File(dirPath);
        dir.mkdirs();
        Button capture = (Button) findViewById(R.id.id_capture_button);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Here, the counter will be incremented each time, and the picture taken by camera
                // will be stored as 1.jpg,2.jpg and likewise.
                sCount++;
                mFilePath = dirPath + sCount + ".jpg";
                File newfile = new File(mFilePath);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                }

                Uri outputFileUri = Uri.fromFile(newfile);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
    }

    private void setCameraImage(Intent data) {
        // Gets the bitmap from the camera.
        Bitmap bitmap = getPhoto(300);
        mImageView.setImageBitmap(bitmap);
    }

    private Bitmap getPhoto(Integer imageSize) {
//    private Bitmap getPhoto(Intent data, Integer imageSize) {
        // Get the image from data.
//        Uri selectedImage = data.getData();
//        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        // Get the cursor and move it to first row.
//        Cursor cursor = mActivity.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        String path = cursor.getString(columnIndex);
//        cursor.close();

        // Set the image in image view after decoding the string.
        Bitmap original = BitmapFactory.decodeFile(mFilePath);
        Integer size = (int)(imageSize * mActivity.getResources().getDisplayMetrics().density);
        Bitmap resized = resizeBitmap(original, size, size);

        return resized;
    }

    public Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // Creates a matrix for the manipulation and resize the bitmap.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        // Recreate the new bitmap.
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return resizedBitmap;
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
                    setCamera();
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
            Boolean hasCameraPermission = PermissionUtils.hasCameraPermission(mActivity);
            Boolean hasWritePermission = PermissionUtils.hasWriteExternalStorage(mActivity);
            if (!hasCameraPermission && !hasWritePermission) {
                Log.i("Test", "checkPermissions() -> Calling requestPermissions().");
                requestPermissions(PERMISSIONS, PERMISSION_REQUEST);
            } else {
                // Permission was granted.
                mHasPermissions = true;
                setCamera();
            }
        } else {
            // Version is below Marshmallow.
            mHasPermissions = true;
            setCamera();
        }
    }
}