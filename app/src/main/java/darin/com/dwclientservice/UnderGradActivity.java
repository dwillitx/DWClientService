package darin.com.dwclientservice;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class UnderGradActivity extends ActionBarActivity {

    private Toolbar actionBarToolBar;
    private String cisPrgName = "undergraduate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_grad);

        // action bar toolbar
        actionBarToolBar = (Toolbar) findViewById(R.id.my_action_toolbar);

        //set first
        setSupportActionBar(actionBarToolBar);

        //set it to navigtion
        actionBarToolBar.setNavigationIcon(R.drawable.ic_action_back_arrow);

        actionBarToolBar.setNavigationContentDescription(getResources().getString(R.string.navigation_icon_description));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflate the menu this is how items are added to the action bar
        getMenuInflater().inflate(R.menu.menu, menu);

        //Hide Icons on Action Bar
        menu.findItem(R.id.menu_undergraduate_prg).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        //Handle actions from menu
        switch (item.getItemId()) {

            case R.id.menu_graduate_prg:

                intent = new Intent(this, GraduateActivity.class);
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
}
