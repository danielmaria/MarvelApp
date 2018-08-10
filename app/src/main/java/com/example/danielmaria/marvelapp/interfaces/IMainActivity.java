package com.example.danielmaria.marvelapp.interfaces;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.view.fragments.ComicFragment;
import com.example.danielmaria.marvelapp.view.fragments.HeroFragment;

public interface IMainActivity {

    interface View {
        void goToHeroFragment();
    }

    interface Presenter {

    }



}
