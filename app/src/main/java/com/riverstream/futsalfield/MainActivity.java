package com.riverstream.futsalfield;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private ActionBar toolbar;
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        //toolbar.setTitle("Home");


        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener((@NonNull MenuItem item)->{
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(getString(R.string.title_home));
                    return true;
                case R.id.navigation_match:
                    toolbar.setTitle(getString(R.string.title_matchs));
                    return true;
                case R.id.navigation_history:
                    toolbar.setTitle(getString(R.string.title_matchs));
                    return true;
                case R.id.navigation_locker_room:
                    toolbar.setTitle(getString(R.string.title_matchs));
                    return true;
            }
            return false;});
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
