package darin.com.dwclientservice.tasks;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Darin on 7/7/2016.
 */
public class CISHelper {

    private static final String cisJsonUrl = "https://api.myjson.com/bins/4zwbz";
    private static final int HTTP_STATUS_OK = 200;
    private static final String logTag = "CISHelper";
    private static byte[] buff = new byte[1024];

    protected static synchronized String downloadFromServer (String... params)
    {

        String results = "";

        Log.i(logTag,"Fetching " + cisJsonUrl + " Params: " + params);


        // create an http client and a request object.
        HttpsURLConnection urlConnection = null;

        try {

            // execute the request
            URL url = new URL(cisJsonUrl);

            //Creatre and open connection
            urlConnection = (HttpsURLConnection) url.openConnection();

            //set headers
            urlConnection.setRequestProperty("Accept", "application/json");

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String s = "";

            while ((s = reader.readLine()) != null) {
                results += s;
            }


        } catch (MalformedURLException e1) {
            Log.i("HtppAsyncTask", "MalformedURLException: " + e1.getMessage());

        } catch (IOException e1) {
            Log.i("HtppAsyncTask", "IOException: " + e1.getMessage());
        }



        return results;
    }
}
