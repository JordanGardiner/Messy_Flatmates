package com.example.messy_flatmates;

import android.app.Activity;
import android.os.Bundle;

import com.example.messy_flatmates.Fragments.Calendar_fragment;
import com.example.messy_flatmates.Fragments.Create_task_fragment;
import com.example.messy_flatmates.Fragments.Group_fragment;
import com.example.messy_flatmates.Fragments.Leaderboard_fragment;
import com.example.messy_flatmates.Fragments.My_task_fragment;
import com.example.messy_flatmates.Fragments.Settings_fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragment_manager = getSupportFragmentManager();

        Calendar_fragment calendar_fragment = new Calendar_fragment();

        calendar_fragment.setArguments(bundle);

        if (id == R.id.nav_calendar) {
            fragment_manager.beginTransaction().replace(R.id.content_frame, calendar_fragment).commit();
        } else if (id == R.id.nav_create_task) {
            fragment_manager.beginTransaction().replace(R.id.content_frame, new Create_task_fragment()).commit();
        } else if (id == R.id.nav_my_tasks) {
            fragment_manager.beginTransaction().replace(R.id.content_frame, new My_task_fragment()).commit();
        } else if (id == R.id.nav_group) {
            fragment_manager.beginTransaction().replace(R.id.content_frame, new Group_fragment()).commit();
        } else if (id == R.id.nav_leaderboard) {
            fragment_manager.beginTransaction().replace(R.id.content_frame, new Leaderboard_fragment()).commit();
        } else if (id == R.id.nav_settings) {
            Settings_fragment settings_fragment = new Settings_fragment();
            settings_fragment.setArguments(bundle);
            fragment_manager.beginTransaction().replace(R.id.content_frame,settings_fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
