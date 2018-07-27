package com.example.danielmaria.marvelapp.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.view.fragments.ComicFragment;
import com.example.danielmaria.marvelapp.view.fragments.HeroFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.nav_characters:
                                selectedFragment =  new HeroFragment();
                                break;
                            case R.id.nav_comics:
                                selectedFragment =  new ComicFragment();
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        goToHeroFragment();
    }

    private void goToHeroFragment(){
        Fragment heroFragment = new HeroFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, heroFragment).commit();
    }


}
