package com.vandit.samples.appcomponents;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.vandit.samples.appcomponents.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        openMainFragment();
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


        // initialize navigation drawer and actionbartoggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                syncActionBarArrowState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

//        mNavigationView.getMenu().getItem(0).setChecked(true);
//        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));

    }

    private void syncActionBarArrowState() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(backStackEntryCount == 0);
    }

    /**
     * Set up action bar
     */
    public void setUpActionBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolBar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
       /* mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        fragmentTransaction.replace(R.id.activity_main_fl_content, mainFragment);
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
            default:
                fragmentClass = MainFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert fragment by replacing any existing fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fl_content, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
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
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

}
