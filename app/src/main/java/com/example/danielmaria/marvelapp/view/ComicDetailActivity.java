package com.example.danielmaria.marvelapp.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.model.Comic;
import com.example.danielmaria.marvelapp.model.Hero;
import com.example.danielmaria.marvelapp.service.HttpService;

public class ComicDetailActivity extends AppCompatActivity {
    private TextView tituloComic;
    private TextView comicIsbnTitle;
    private TextView titlePageCount;
    private Comic comic;
    private HttpService httpService;
    private ConstraintLayout errorMessage;
    private ProgressBar progressBar;
    private ImageView imgComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        progressBar = findViewById(R.id.progress_bar);
        errorMessage = findViewById(R.id.error_message);
        tituloComic = findViewById(R.id.titulo_comic);
        comicIsbnTitle = findViewById(R.id.comic_isbn_title);
        titlePageCount = findViewById(R.id.title_page_count);
        imgComic = findViewById(R.id.img_comic);

        this.comic = (Comic) getIntent().getSerializableExtra("comic");
        httpService = new HttpService();

        httpService.getComicById(this.comic.getId(), new HttpService.GetComicrByIdListener(){
            @Override
            public void sucess(Comic comicRequest) {
                progressBar.setVisibility(View.GONE);
                comic = comicRequest;
                tituloComic.setText(comic.getName());
                comicIsbnTitle.setText(!comic.getIsbn().isEmpty() ? "ISBN: " + comic.getIsbn() : "");
                titlePageCount.setText(String.valueOf(comic.getPageCount()));

                setImageComic();
            }
            @Override
            public void fail() {
                errorMessage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void setImageComic() {
        imgComic.setContentDescription("Imagem do " + comic.getName());
        Glide.with(this).load(comic.getThumbnail()).into(imgComic);
    }
}
