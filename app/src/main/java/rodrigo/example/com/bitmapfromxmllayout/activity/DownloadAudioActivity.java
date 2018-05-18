package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import rodrigo.example.com.bitmapfromxmllayout.R;
import rodrigo.example.com.bitmapfromxmllayout.utils.PermissionUtils;

public class DownloadAudioActivity extends Activity {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    /**
     * Permissions.
     */

    private static final String[] PERMISSIONS = {
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private static final int PERMISSION_REQUEST = 10000;

    /**
     * File Path.
     */

    private static final String FILENAME = "file.mp3";
    private static final String URL = "http://www.virginmegastore.me/Library/Music/CD_001214/Tracks/Track1.mp3";

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Context.
     */

    private Activity mActivity = DownloadAudioActivity.this;

    /**
     * Progress.
     */

    private ProgressDialog mProgressDialog;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();
    }

    //--------------------------------------------------
    // Async Task
    //--------------------------------------------------

    @SuppressWarnings("deprecation")
    public class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setMessage("Downloading file. Please wait...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressDrawable(ContextCompat.getDrawable(mActivity, R.drawable.progress_bar_states));
            mProgressDialog.show();
        }

        /**
         * Downloading file in background thread.
         */
        protected String doInBackground(String... urls) {
            int count;
            try {
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // Getting file length and input stream to read file (with 8k buffer).
                int length = connection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file.
                File audioDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                String path = audioDirectory.getPath();
                OutputStream output = new FileOutputStream(path + "/" + FILENAME);

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Publishing the progress. After this onProgressUpdate will be called.
                    publishProgress("" + (int) ((total * 100) / length));
                    // Writing data to file.
                    output.write(data, 0, count);
                }
                // Flushing output and closing streams.
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        /**
         * Updating progress bar.
         */
        protected void onProgressUpdate(String... progress) {
            // Setting progress percentage.
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog.
         **/
        @Override
        protected void onPostExecute(String path) {
            // Dismiss the dialog after the file was downloaded.
            mProgressDialog.cancel();
            Log.i("Test", "File downloaded and saved to directory '" + path + "'.");
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
                Boolean permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (grantResults.length > 0 && permissionGranted) {
                    // Permission was granted.
                    new DownloadFileFromURL().execute(URL);
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
            Boolean canRecordAudio = PermissionUtils.hasRecordAudio(mActivity);
            Boolean canWriteExternalStorage = PermissionUtils.hasWriteExternalStorage(mActivity);
            if (!canRecordAudio || !canWriteExternalStorage) {
                requestPermissions(PERMISSIONS, PERMISSION_REQUEST);
            } else {
                // Permission was granted.
                new DownloadFileFromURL().execute(URL);
            }
        } else {
            // Version is below Marshmallow.
            new DownloadFileFromURL().execute(URL);
        }
    }
}