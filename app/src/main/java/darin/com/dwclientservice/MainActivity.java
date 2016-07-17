package darin.com.dwclientservice;


import android.app.Fragment;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.NavigationView;

import java.util.ArrayList;

import darin.com.dwclientservice.adapters.CourseAdapter;
import darin.com.dwclientservice.model.RegisData;
import darin.com.dwclientservice.tasks.CISBackGoundTask;

public class MainActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<RegisData> regisDataList;
    private ListView regisListview;
    private LayoutInflater layoutInflator;
    private Button courseButton;

    private Toolbar actionBarToolBar;
    private String[] drawerListViewItems;
    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;


       if (id == R.id.nav_chat) {
            // Handle the camera action
       } else if (id == R.id.nav_certificate) {

           processJSON("certificate");

        } else if (id == R.id.nav_grad_prg) {

           processJSON("graduate");

        } else if (id == R.id.nav_under_prg) {

           processJSON("undergraduate");

        } else if (id == R.id.nav_social) {

        } else if (id == R.id.nav_feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this,
                    ((TextView) view).getText(),
                    Toast.LENGTH_LONG).show();


            //Since I am using a fragment to insert information into a framelayout
            //it is being shared by the list created from the navigational drawer
            //and is by the gridview, the gridview needs to be set to gone when
            //information from drawer is display and set to visible when home is pressed

            drawerLayout.closeDrawer(drawerListView);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // action bar toolbar
        actionBarToolBar = (Toolbar) findViewById(R.id.my_action_toolbar);

        //set first
        setSupportActionBar(actionBarToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set it to navigtion
        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_back_arrow);

        actionBarToolBar.setNavigationContentDescription(getResources().getString(R.string.navigation_icon_description));

        //set the logo
        actionBarToolBar.setLogo(R.mipmap.ic_nav_regis);
        actionBarToolBar.setLogoDescription(getResources().getString(R.string.logo_description));


        //This will get the intent than check the action

        Intent searchIntent = getIntent();
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {

            //Get the text typed in for the Search
            String query = searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
        }



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView nview = (NavigationView) findViewById(R.id.nav_view);
        nview.setNavigationItemSelectedListener(this);


        //if the mainAcitvy is newly created, use the selectItem method to
          if (savedInstanceState == null) {
//            selectItem(0);
        }


        //toogle the action toolbar and pass in all the require parameters
        //you must pass in the actionBarToolBar so it will open and close
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                actionBarToolBar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        actionBarDrawerToggle.syncState();


        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu this is how items are added to the action bar
        getMenuInflater().inflate(R.menu.menu, menu);


        //Create the searchview

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        //Get the search manager from the system
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);

        //bind the searchView object with the searchable component
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        //Handle actions from menu
        switch (item.getItemId()) {

            case R.id.menu_graduate_prg:

                processJSON("graduate");

                return true;


            case R.id.menu_undergraduate_prg:

               processJSON("undergraduate");

                return true;

            case R.id.menu_chat:

            case R.id.menu_feedback_rating:

            case R.id.menu_social_media:

            case R.id.menu_search:

            case R.id.menu_certificates:

                processJSON("certificate");

                return true;


        }

        return super.onOptionsItemSelected(item);


    }


    public void processJSON(String cisPrgName) {


        CISBackGoundTask cisBackGoundTask = new CISBackGoundTask(MainActivity.this);

        try {


            cisBackGoundTask.execute(cisPrgName);

        } catch (Exception e) {

        cisBackGoundTask.cancel(true);

      }

    }

}
