package darin.com.dwclientservice;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import darin.com.dwclientservice.tasks.CISBackGoundTask;
import darin.com.dwclientservice.tasks.CISHelper;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends InstrumentationTestCase {

    private static final String cisJsonUrl = "https://api.myjson.com/bins/4zwbz";
    private static final int HTTP_STATUS_OK = 200;
    CISBackGoundTask cisBg;
    public String logTag = "ExampleUnitTest";
    CISHelper cisHelper;


    @Before
    public void setUp(){
        cisBg = new CISBackGoundTask(cisJsonUrl);
        cisHelper = new CISHelper();

    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testDownloadFromServer() {

    //        String cisJsonUrl = "https://api.myjson.com/bins/4zwbz";

//    Log.d(logTag, "... Starting... ");

        try {

            cisBg.execute(cisJsonUrl);


        } catch (Exception e) {
         e.printStackTrace();
        }
    }

    /**
     * This demonstrates how to test AsyncTasks in android JUnit. Below I used
     * an in line implementation of a asyncTask, but in real life you would want
     * to replace that with some task in your application.
     * @throws Throwable
     */
    @Test
    public void testSomeAsynTask () throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

    /* Just create an in line implementation of an asynctask. Note this
     * would normally not be done, and is just here for completeness.
     * You would just use the task you want to unit test in your project.
     */
        final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                try {
                    Log.d("logTag", "Background:" + Thread.currentThread().getName());
                    String result =    downloadFromServer((cisJsonUrl));

                    assertNull(result);
                    return result;
                } catch (Exception e) {
                    return new String();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

            /* This is the key, normally you would use some type of listener
             * to notify your activity that the async call was finished.
             *
             * In your test method you would subscribe to that and signal
             * from there instead.
             */
                signal.countDown();
            }
        };

        // Execute the async task on the UI thread! THIS IS KEY!
        runTestOnUiThread(new Runnable() {


            @Override
            public void run() {
                myTask.execute("Do something");
            }
        });

    /* The testing thread will wait here until the UI thread releases it
     * above with the countDown() or 30 seconds passes and it times out.
     */
        signal.await(30, TimeUnit.SECONDS);

        // The task is done, and now you can assert some things!
        assertTrue("Happiness", true);
    }


    protected static synchronized String downloadFromServer (String... params)
    {

        String results = "";

        Log.d("logTag","  Fetching " + cisJsonUrl + "  Params: " + params);

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





