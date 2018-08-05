package com.riverstream.futsalfield;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.riverstream.futsalfield.ui.cari_lawan.CariLawanFragment;
import com.riverstream.futsalfield.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Home");

        loadFragment(new HomeFragment());
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener((@NonNull MenuItem item)->{
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(getString(R.string.title_home));
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_match:
                    toolbar.setTitle(getString(R.string.title_matchs));
                    fragment = new CariLawanFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_history:
                    toolbar.setTitle(getString(R.string.title_matchs));
                    fragment = new CariLawanFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_locker_room:
                    toolbar.setTitle(getString(R.string.title_matchs));
                    fragment = new CariLawanFragment();
                    loadFragment(fragment);
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
