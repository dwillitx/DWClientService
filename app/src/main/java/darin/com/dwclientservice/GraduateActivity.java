package darin.com.dwclientservice;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import darin.com.dwclientservice.adapters.CourseAdapter;
import darin.com.dwclientservice.model.RegisData;
import darin.com.dwclientservice.tasks.CISBackGoundTask;

public class GraduateActivity extends ActionBarActivity {

    private ArrayList<RegisData> regisDataList;
    private Toolbar actionBarToolBar;
    private ListView regisListview;
    String cisPrgName = "graduate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduate);

        // action bar toolbar
        actionBarToolBar = (Toolbar) findViewById(R.id.my_action_toolbar);

        //set first
        setSupportActionBar(actionBarToolBar);

        //set it to navigtion
        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_back_arrow);

        actionBarToolBar.setNavigationContentDescription(getResources().getString(R.string.navigation_icon_description));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        CISBackGoundTask cisBackGoundTask = new CISBackGoundTask(GraduateActivity.this);

        try {


            cisBackGoundTask.execute(cisPrgName);


        } catch (Exception e) {

            cisBackGoundTask.cancel(true);
            alert(getResources().getString(R.string.data_error));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflate the menu this is how items are added to the action bar
        getMenuInflater().inflate(R.menu.menu, menu);

        //Hide Icons on Action Bar
        menu.findItem(R.id.menu_graduate_prg).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        //Handle actions from menu
        switch (item.getItemId()) {

            case R.id.menu_undergraduate_prg:

                intent = new Intent(this, UnderGradActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_chat:

            case R.id.menu_feedback_rating:

            case R.id.menu_social_media:

            case R.id.menu_search:

            case R.id.menu_certificates:

                intent = new Intent(this, CertificateActivity.class);
                startActivity(intent);

                return true;

        }

        return super.onOptionsItemSelected(item);


    }

    /**
     * Handy dandy alerter.
     *
     * @param msg the message to toast.
     */
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


}

