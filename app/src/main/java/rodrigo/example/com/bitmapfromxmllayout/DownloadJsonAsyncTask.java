package rodrigo.example.com.bitmapfromxmllayout;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * DownloadImageAsyncTask.java.
 *
 * @author Rodrigo Cericatto
 * @since Mar 27, 2016
 */
public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {

    //--------------------------------------------------
    // Async Task
    //--------------------------------------------------

    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        String link = urls[0];
        try {
            URL url = new URL(link);

            // Read all the text returned by the server.
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            while ((s = in.readLine()) != null) {
                response += s;
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}