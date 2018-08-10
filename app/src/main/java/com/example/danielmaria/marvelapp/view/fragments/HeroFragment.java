package com.example.danielmaria.marvelapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.interfaces.fragments.IHeroFragment;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.presenter.fragments.HeroFragmentPresenter;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.HeroDetailActivity;
import com.example.danielmaria.marvelapp.view.adapter.HeroesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroFragment extends Fragment implements IHeroFragment.View {

    private IHeroFragment.Presenter presenter = new HeroFragmentPresenter(this);

    @BindView(R.id.hero_recycler) RecyclerView herosRecycler;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.error_message) ConstraintLayout errorMessage;

    private HeroesAdapter heroesAdapter;
    private HttpService httpService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hero_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        httpService = new HttpService();
        ButterKnife.bind(view);

        presenter.getComics();

    }

    @Override
    public void showErrorMessage() {
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(List<Hero> heroes) {
        heroesAdapter = new HeroesAdapter(getContext(), heroes, new HeroesAdapter.OnHeroClicked() {
            @Override
            public void onClick(Hero hero) {
                Intent intent = new Intent(getActivity(), HeroDetailActivity.class);
                intent.putExtra("hero", hero);

                startActivity(intent);
            }
        });
        herosRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        herosRecycler.setAdapter(heroesAdapter);
    }
}
