package JSON;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by E-R-C on 10/15/2016.
 */

public class NetworkUtil {
    private static String LOG_TAG = "NETWORK_UTIL";
    public static String getJSONFrom(String urlIn){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            StringBuilder sb = new StringBuilder();
            URL url = new URL(urlIn);
            System.out.println(url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            int problem = urlConnection.getResponseCode();
            urlConnection.connect();
            if (problem != 200){
                System.out.println("The error code is " + problem);
            }
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                Log.e(LOG_TAG,"Input Stream Null");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0){
                Log.e(LOG_TAG,"Buffer was empty");
                return null;
            }
            return buffer.toString();

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Invalid URL!");
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG,"Invalid urlConnection!");
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG,"Error Closing Stream");
                }
            }
        }
    }
}
