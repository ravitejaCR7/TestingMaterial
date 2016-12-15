package com.example.talarir.testingmaterial;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import layout.FragmentsList.FragmentOne;
import layout.FragmentsList.FragmentThree;
import layout.FragmentsList.FragmentTwo;

public class CoordinationLayoutActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordination_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager=(ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout= (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setUpCustomTabIcons();

    }

    private void setUpCustomTabIcons()
    {
        TextView tabText1=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout,null);
        tabText1.setText("ONE");
        tabText1.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.ic_one,0,0);
        tabLayout.getTabAt(0).setCustomView(tabText1);

        TextView tabText2=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout,null);
        tabText2.setText("TWO");
        tabText2.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.ic_two,0,0);
        tabLayout.getTabAt(1).setCustomView(tabText2);

        TextView tabText3=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout,null);
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
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
