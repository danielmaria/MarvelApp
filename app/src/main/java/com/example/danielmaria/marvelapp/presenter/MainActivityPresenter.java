package com.example.danielmaria.marvelapp.presenter;

import com.example.danielmaria.marvelapp.interfaces.IMainActivity;
import com.example.danielmaria.marvelapp.view.MainActivity;

public class MainActivityPresenter implements IMainActivity.Presenter {

    private MainActivity view;

    public MainActivityPresenter(MainActivity view){
        this.view = view;
    }


}
