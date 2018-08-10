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
import com.example.danielmaria.marvelapp.interfaces.IMainActivity;
import com.example.danielmaria.marvelapp.presenter.MainActivityPresenter;
import com.example.danielmaria.marvelapp.view.fragments.ComicFragment;
import com.example.danielmaria.marvelapp.view.fragments.HeroFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements IMainActivity.View {

    @BindView(R.id.bottom_nav_view) BottomNavigationView bottomNavigationView;

    /* Presenter */
    private IMainActivity.Presenter presenter = new MainActivityPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.bottomNavigationView.setOnNavigationItemSelectedListener
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

    @Override
    public void goToHeroFragment() {
        Fragment heroFragment = new HeroFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, heroFragment).commit();
    }
}
