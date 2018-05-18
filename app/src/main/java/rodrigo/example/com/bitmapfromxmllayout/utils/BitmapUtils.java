package rodrigo.example.com.bitmapfromxmllayout.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

import rodrigo.example.com.bitmapfromxmllayout.BuildConfig;
import rodrigo.example.com.bitmapfromxmllayout.Globals;

public class BitmapUtils {

    //--------------------------------------------------
    // Thumbnail Methods
    //--------------------------------------------------

    public static String getVideoThumbnailPath(Activity activity, String path) {
        // Output stream to write file.
        Bitmap thumbnail = getVideoThumbnail(activity, path, false);
        String videoPath = getFilePath(path);
        File file = new File(videoPath);
        File dir = new File(file.getParent());
        if (thumbnail != null) {
            try {
                // Build directory.
                if (file.getParent() != null && !dir.isDirectory()) {
                    dir.mkdirs();
                }
                // Output image to file.
                FileOutputStream fileOutputStream = new FileOutputStream(videoPath);
                thumbnail.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.close();
            } catch (Exception e) {
                Log.e("Test", "BitmapUtils.getVideoThumbnailPath() -> Error! " + e.getMessage() + ".");
            }
        } else {
            Log.e("Test", "BitmapUtils.getVideoThumbnailPath() -> Save picture image parsing error.");
        }
        return videoPath;
    }

    private static Bitmap getVideoThumbnail(Activity activity, String path, Boolean resize) {
        File file = new File(path);
        Bitmap thumbnail = null;
        if (file.exists()) {
            thumbnail = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
            if (resize) {
                Integer size = (int)dpToPixels(activity, 120f);
                thumbnail = resizeBitmap(thumbnail, size, size);
            }
        }
        return thumbnail;
    }

    private static String getFilePath(String path) {
        String videoDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        videoDirectory += Globals.PREFIX_DIRECTORY + BuildConfig.APPLICATION_ID + Globals.VIDEO_DIRECTORY;
        File dir = new File(videoDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String[] parts = path.split("/");
        String completeFileName = parts[parts.length - 1];
        String[] fileTypeParts = completeFileName.split("\\.");
        String fileName = fileTypeParts[0];
        String videoPath = dir.getPath() + "/" + fileName + ".png";

        return videoPath;
    }

    private static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
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

    private static float dpToPixels(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}