package darin.com.dwclientservice.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import android.widget.Toast;


import darin.com.dwclientservice.DisplayListView;


/**
 * Created by Darin on 7/8/2016.
 */
public class CISBackGoundTask extends AsyncTask<String, Void, String> {

    private static final String logTag = "CISBackGroundTask";
    private Activity activity;
    private Context context;
    private String urlObj;


    public CISBackGoundTask(Activity activity) {
        super();
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    @Override
    protected void onPreExecute() { super.onPreExecute();   }

    @Override
    protected String doInBackground(String... params) {

        try {
            Log.d(logTag, "Background:" + Thread.currentThread().getName());

            //Set name of Array
            this.urlObj = params[0];

            String result = CISHelper.downloadFromServer(params);
            return result;
        } catch (Exception e) {
            return new String();
        }

    }


    @Override
    protected void onPostExecute(String objStr) {


        if (objStr.length() == 0) {
            Log.i(logTag, " obj length does not contain any data");
            Toast.makeText(context, "No JSON Data Available", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(context, DisplayListView.class);
            intent.putExtra("json_data", objStr);
            intent.putExtra("json_arrayName", urlObj);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
}
