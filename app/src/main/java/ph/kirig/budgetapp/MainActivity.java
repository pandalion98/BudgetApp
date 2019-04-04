package ph.kirig.budgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import ph.kirig.budgetapp.home_ui.home.HomeFragment;
import ph.kirig.budgetapp.home_ui.transactionlist.TransactionListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String FRAGMENT_HOME_TAG = "frag_home";
    private static final String FRAGMENT_TXLIST_TAG = "frag_tx_list";

    private FragmentManager fragmentManager;
    private int fragmentDepth = 0; // 0 = Home Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            // Add the initial fragment
            fragmentManager.beginTransaction()
                    .add(R.id.container, HomeFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fragmentDepth == 1) {
            // Go back to home
            fragmentDepth = 0;
            //fragmentManager.popBackStack(FRAGMENT_HOME_TAG, 0); // 0 = Non-inclusive popping
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Keep track of current stack depth
        fragmentDepth = 1;

        if (id == R.id.nav_tx_list) {
            // Remove top fragment since we don't need it anymore (we're going home on back press)
            fragmentManager.popBackStackImmediate();

            // Create new fragment and transaction
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new TransactionListFragment())
                    .addToBackStack(null)
                    .commit(); // Commit the transaction
        } else if (id == R.id.nav_gallery) {
            // Remove top fragment since we don't need it anymore (we're going home on back press)
            fragmentManager.popBackStackImmediate();

            // Create new fragment and transaction
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new TransactionListFragment())
                    .addToBackStack(FRAGMENT_TXLIST_TAG)
                    .commit(); // Commit the transaction
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, AddCurrencyActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivity.this, AddAccountActivity.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this, AddTransactionActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
