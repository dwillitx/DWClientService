package darin.com.dwclientservice;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import darin.com.dwclientservice.adapters.CourseAdapter;
import darin.com.dwclientservice.model.RegisData;

public class DisplayListView extends ActionBarActivity {

    String json_string, json_arryName;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CourseAdapter courseAdapter;
    ListView listView;
    private Toolbar actionBarToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        // action bar toolbar
        actionBarToolBar = (Toolbar) findViewById(R.id.my_action_toolbar);

        //set first
        setSupportActionBar(actionBarToolBar);

        //set it to navigtion
        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_back_arrow);

        actionBarToolBar.setNavigationContentDescription(getResources().getString(R.string.navigation_icon_description));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //Initialize adapater with data
        courseAdapter = new CourseAdapter(this, R.layout.course_row);

        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();

        json_string = getIntent().getExtras().getString("json_data");
        json_arryName = getIntent().getExtras().getString("json_arrayName");
        int count = 0;
        String name;

        try {
            jsonObject = new JSONObject(json_string);

            //Get name of Array off intent
            jsonArray = jsonObject.getJSONArray(json_arryName);



            while ( count < jsonArray.length()){

                JSONObject json_currentObj  = jsonArray.getJSONObject(count);

                //Get name of variable off Json array
                name = json_currentObj.getString("name");

                //Store in domain object
                RegisData regisData = new RegisData(name);

                //Add domain object to adapter
                courseAdapter.add(regisData);

                //Advance to next record in arraylist
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




}











