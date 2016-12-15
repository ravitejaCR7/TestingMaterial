package com.example.talarir.testingmaterial;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.talarir.testingmaterial.MixedFruitJuice.MixedFruitJuice;
import com.example.talarir.testingmaterial.navigationactivities.MainNavigation;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar,t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationFragmant navigationFragmant=(NavigationFragmant) getSupportFragmentManager().findFragmentById(R.id.nav_fragment);
        navigationFragmant.setup(R.id.nav_fragment,(DrawerLayout) findViewById(R.id.navigationDrawer),toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id== R.id.menu_coordination)
        {
            Toast.makeText(this,"WTFFFFFFFFF",Toast.LENGTH_SHORT).show();
            Intent coordinationLayoutIntent = new Intent(this,CoordinationLayoutActivity.class);
            startActivity(coordinationLayoutIntent);
        }
        if (id==R.id.menu_navigation)
        {
            Toast.makeText(this,"navigation",Toast.LENGTH_SHORT).show();
            Intent realNavigation= new Intent(this, MainNavigation.class);
            startActivity(realNavigation);
        }
        if (id==R.id.menu_mixed)
        {
            Toast.makeText(this,"mixed fruit juice",Toast.LENGTH_SHORT).show();
            Intent realMixedStarts= new Intent(this, MixedFruitJuice.class);
            startActivity(realMixedStarts);
        }
        return super.onOptionsItemSelected(item);
    }
}
