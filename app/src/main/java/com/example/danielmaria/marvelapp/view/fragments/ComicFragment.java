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
import com.example.danielmaria.marvelapp.interfaces.fragments.IComicFragment;
import com.example.danielmaria.marvelapp.presenter.fragments.ComicFragmentPresenter;
import com.example.danielmaria.marvelapp.view.adapter.ComicsAdapter;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.service.HttpService;
import com.example.danielmaria.marvelapp.view.ComicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicFragment extends Fragment implements IComicFragment.View{

    private IComicFragment.Presenter presenter = new ComicFragmentPresenter(this);
    private ComicsAdapter comicsAdapter;


    @BindView(R.id.comic_reycler) private RecyclerView comicsRecycler;
    @BindView(R.id.progress_bar) private ProgressBar progressBar;
    @BindView(R.id.error_message) private ConstraintLayout errorMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comic_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void setAdapter(List<Comic> comicsList) {
        comicsAdapter = new ComicsAdapter(getContext(), comicsList, new ComicsAdapter.OnComicClicked() {
            @Override
            public void onClick(Comic comic) {
                Intent intent = new Intent(getActivity(), ComicDetailActivity.class);
                intent.putExtra("comic", comic);

                startActivity(intent);
            }
        });
        comicsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        comicsRecycler.setAdapter(comicsAdapter);
    }
}
