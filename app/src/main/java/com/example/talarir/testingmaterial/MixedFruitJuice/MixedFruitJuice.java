package com.example.talarir.testingmaterial.MixedFruitJuice;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talarir.testingmaterial.CoordinationLayoutActivity;
import com.example.talarir.testingmaterial.R;
import com.example.talarir.testingmaterial.navigationfragments.HomeFragment;
import com.example.talarir.testingmaterial.navigationfragments.MovieFragment;

import java.util.ArrayList;
import java.util.List;

import layout.FragmentsList.FragmentOne;
import layout.FragmentsList.FragmentThree;
import layout.FragmentsList.FragmentTwo;

public class MixedFruitJuice extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private FloatingActionButton fab;

    public static int urlNavHeaderBg=R.drawable.main;
    public static int urlProfileImg=R.drawable.messi;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_MOVIES = "movies";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixed_fruit_juice);

        toolbar=(Toolbar)findViewById(R.id.toolbarMFJ);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager=(ViewPager)findViewById(R.id.viewpagerMFJ);
        setupViewPager(viewPager);

        tabLayout= (TabLayout)findViewById(R.id.tabsMFJ);
        tabLayout.setupWithViewPager(viewPager);
        setUpCustomTabIcons();

        //code from that file

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mfj);
        navigationView = (NavigationView) findViewById(R.id.nav_viewMFJ);
        fab = (FloatingActionButton) findViewById(R.id.fabMFJ);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }


    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button



            toggleFab();



            return;
        }



        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }


    private void loadNavHeader() {
        // name, website
        txtName.setText("RONALDO");
        txtWebsite.setText("www.EpicAppDevelopers.com");
        txtName.setTextColor(ContextCompat.getColor(this,R.color.navigationHeaderTextCOlor));
        txtWebsite.setTextColor(ContextCompat.getColor(this,R.color.navigationHeaderTextCOlor));

        imgNavHeaderBg.setImageResource(urlNavHeaderBg);
        imgProfile.setImageResource(urlProfileImg);

    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // movies fragment
                MovieFragment moviesFragment = new MovieFragment();
                return moviesFragment;

            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        Toast.makeText(getApplicationContext(),"EAD",Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        Toast.makeText(getApplicationContext(),"EAD POLICIES",Toast.LENGTH_SHORT).show();
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }




    private void setUpCustomTabIcons()
    {
        TextView tabText1=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout_mfj,null);
        tabText1.setText("ONE");
        tabText1.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.ic_one,0,0);
        tabLayout.getTabAt(0).setCustomView(tabText1);

        TextView tabText2=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout_mfj,null);
        tabText2.setText("TWO");
        tabText2.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.ic_two,0,0);
        tabLayout.getTabAt(1).setCustomView(tabText2);

        TextView tabText3=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout_mfj,null);
        tabText3.setText("THREE");
        tabText3.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.ic_three,0,0);
        tabLayout.getTabAt(2).setCustomView(tabText3);

    }

    private void setupViewPager(ViewPager viewPager)
    {

        ViewPageAdapter vpAdapter = new ViewPageAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new FragmentOne(),"one");
        vpAdapter.addFragment(new FragmentTwo(),"two");
        vpAdapter.addFragment(new FragmentThree(),"three");
        viewPager.setAdapter(vpAdapter);

    }
    class ViewPageAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPageAdapter(android.support.v4.app.FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        public CharSequence getPageTitle(int position)
        {
            return null;
        }
    }

}
