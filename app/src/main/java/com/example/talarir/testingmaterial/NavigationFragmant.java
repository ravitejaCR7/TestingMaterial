package com.example.talarir.testingmaterial;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NavigationFragmant extends android.support.v4.app.Fragment {


    private static final String SHARED_FILE_NAME = "raviSP";
    private static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
    ActionBarDrawerToggle drawerToggle;
    public View containerView;

    private boolean mUserLearnedNavDrawer;
    private boolean mFromSavedInstanceState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedNavDrawer=Boolean.valueOf(getThePreference(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));
        if (savedInstanceState!=null)
        {
            mFromSavedInstanceState=true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_fragmant, container, false);
    }


    public void setup(int fragId,DrawerLayout drawerLayot, Toolbar toolbar)
    {
        containerView=getActivity().findViewById(fragId);
        drawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayot,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedNavDrawer)
                {
                    mUserLearnedNavDrawer=true;
                    saveToPref(getContext(),KEY_USER_LEARNED_DRAWER,mUserLearnedNavDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if (!mUserLearnedNavDrawer&& !mFromSavedInstanceState)
        {
            drawerLayot.openDrawer(containerView);
        }
        //drawerLayot.setStatusBarBackground(R.color.colorAccent);
        drawerLayot.addDrawerListener(drawerToggle);
        drawerLayot.post(new Runnable() {
            @Override
            public void run()
            {
                drawerToggle.syncState();
            }
        });
    }

    public static void saveToPref(Context context,String prefName, String prefValue)
    {
        SharedPreferences sharedPreference = context.getSharedPreferences(SHARED_FILE_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreference.edit();
        editor.putString(prefName,prefValue);
        editor.commit();
    }
    public static String getThePreference(Context context, String prefName, String defaultValue)
    {
        SharedPreferences sharedPreference = context.getSharedPreferences(SHARED_FILE_NAME,context.MODE_PRIVATE);
        return sharedPreference.getString(prefName,defaultValue);
    }
}
