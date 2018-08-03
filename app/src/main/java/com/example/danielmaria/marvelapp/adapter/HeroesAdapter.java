package com.example.danielmaria.marvelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danielmaria.marvelapp.R;
import com.example.danielmaria.marvelapp.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroesAdapter extends RecyclerView.Adapter {

    private List<Hero> heros;
    private Context context;

    public interface OnHeroClicked{
        void onClick(Hero hero);
    }

    OnHeroClicked onHeroClicked;

    public HeroesAdapter(Context context, List<Hero> heroes, OnHeroClicked onHeroClicked) {
        this.context = context;
        this.heros = heroes;
        this.onHeroClicked = onHeroClicked;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.heroes_item, parent, false);

        return new HerosViewHolder(view, onHeroClicked, heros);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HerosViewHolder holder = (HerosViewHolder) viewHolder;

        Hero hero = heros.get(position);

        holder.heroName.setText(hero.getName());
        holder.heroId.setText(String.valueOf(hero.getId()));
        setImageHero(holder, hero);
    }

    @Override
    public int getItemCount() {
        return heros.size();
    }

    private void setImageHero(HerosViewHolder holder, Hero hero) {
        holder.backgroundHero.setContentDescription("Imagem do " + hero.getName());
        Glide.with(this.context).load(hero.getThumbnail()).into(holder.backgroundHero);
    }
}

class HerosViewHolder extends RecyclerView.ViewHolder{

    TextView heroName;
    TextView heroId;
    ImageView backgroundHero;

    public HerosViewHolder(View itemView, final HeroesAdapter.OnHeroClicked onHeroClicked, final List<Hero> heros) {
        super(itemView);

        this.heroName = itemView.findViewById(R.id.heroe_name);
        this.heroId = itemView.findViewById(R.id.hero_id);
        this.backgroundHero = itemView.findViewById(R.id.background_hero);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHeroClicked.onClick(heros.get(getAdapterPosition()));
            }
        });

    }
}
