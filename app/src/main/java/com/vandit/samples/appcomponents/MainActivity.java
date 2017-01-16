package com.vandit.samples.appcomponents;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.vandit.samples.appcomponents.callbacks.OnFragmentInteractionListner;
import com.vandit.samples.appcomponents.constants.AppConstants;
import com.vandit.samples.appcomponents.fragments.ChildFragment;
import com.vandit.samples.appcomponents.fragments.MainFragment;
import com.vandit.samples.appcomponents.fragments.RecyclerViewFragment;
import com.vandit.samples.appcomponents.fragments.RecyclerViewMainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        FragmentManager.OnBackStackChangedListener, OnFragmentInteractionListner {

    private Toolbar mToolBar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mContentView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if(savedInstanceState == null) {
            openMainFragment();
        }
    }

    private void initViews() {
        // initialize all views
        // Initializing toolbar and set it as actionbar
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolBar != null) {
            setUpActionBar();
        }

        // initialize content view
        mContentView = (FrameLayout) findViewById(R.id.activity_main_fl_content);

        // initialize navigation view
        mNavigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // initialize navigation drawer and actionbar toggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                syncActionBarArrowState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        // Handle toolbar action up click
        mActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        mNavigationView.getMenu().getItem(0).setChecked(true);
//        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    /**
     * Sync actionbar navigation icon based on fragments in backstack.
     */
    private void syncActionBarArrowState() {
        boolean isBackStackEntryEmpty = (getSupportFragmentManager().getBackStackEntryCount() == 0);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(isBackStackEntryEmpty);
        if(!isBackStackEntryEmpty){
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    /**
     * Set up action bar
     */
    public void setUpActionBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolBar.setNavigationIcon(R.drawable.ic_back);
        /*mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toolbar Navigation Click..", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(!item.isChecked()) {
            selectDrawerItem(item);
            return true;
        }
        closeNavigationDrawer();
        return false;
    }

    private void closeNavigationDrawer(){
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }
    }
    /**
     * Open Main fragment
     */
    public void openMainFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MainFragment mainFragment = MainFragment.newInstance();
        fragmentTransaction.replace(R.id.activity_main_fl_content, mainFragment, mainFragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    /**
     * Select drawer item and replace fragment based on item selection.
     *
     * @param item - menu item from navigation view
     */
    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.fragment:
                fragmentClass = MainFragment.class;
                break;
            case R.id.recycler_view:
                fragmentClass = RecyclerViewMainFragment.class;
                break;
            default:
                fragmentClass = MainFragment.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert fragment by replacing any existing fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fl_content, fragment, fragment.getClass().getSimpleName());
//        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();

        //Highlight the selected item in navigation view
        item.setChecked(true);

        // Set actionbar title
        setTitle(item.getTitle());

        // Close navigation drawer
        closeNavigationDrawer();
        Snackbar.make(mContentView, fragmentClass.getSimpleName() + " Opened", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            int noOfFragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
            if(noOfFragmentsInStack > 0){
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onBackStackChanged() {
        syncActionBarArrowState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if(mActionBarDrawerToggle != null){
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop backstack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        if(fragment != null){
            if(fragment instanceof MainFragment){
                // Open child fragment
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                ChildFragment childFragment = ChildFragment.newInstance();
                fragmentTransaction.replace(R.id.activity_main_fl_content, childFragment, ChildFragment.class.getSimpleName());
                fragmentTransaction.addToBackStack(MainFragment.class.getSimpleName());
                fragmentTransaction.commit();
            } else if (fragment instanceof ChildFragment){
                // Handle click of child fragment clicks
            }
        }
    }

    @Override
    public void onFragmentInteraction(Fragment fragment, int id) {
        if(fragment != null){
            if(fragment instanceof RecyclerViewMainFragment){
                if(id > 0){
                    switch (id){
                        case R.id.fragment_recycler_view_main_btn_grid:
                            openRecyclerViewFragmentBasedOnId(AppConstants.RECYCLER_VIEW_GRID, getString(R.string.recycler_view_grid));
                            break;
                        case R.id.fragment_recycler_view_main_btn_list:
                            openRecyclerViewFragmentBasedOnId(AppConstants.RECYCLER_VIEW_LIST, getString(R.string.recycler_view_list));
                            break;
                        case R.id.fragment_recycler_view_main_btn_staggered:
                            openRecyclerViewFragmentBasedOnId(AppConstants.RECYCLER_VIEW_STAGGERED, getString(R.string.recycler_view_staggered));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * Open {@link RecyclerViewFragment} and pass selected view to display data in recycler view
     * @param selectedView Selected view to display in recycler view.
     * @param title Actionbar title.
     */
    private void openRecyclerViewFragmentBasedOnId(int selectedView, String title){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RecyclerViewFragment recyclerViewFragment = RecyclerViewFragment.newInstance(selectedView, title);
        fragmentTransaction.replace(R.id.activity_main_fl_content, recyclerViewFragment, RecyclerViewFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(RecyclerViewMainFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
