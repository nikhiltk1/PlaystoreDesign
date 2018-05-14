package com.jiotask.nikhil.jiotask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiotask.nikhil.jiotask.R;
import com.jiotask.nikhil.jiotask.model.Game;

import java.util.ArrayList;

/**
 * Created by Nikhil on 12-05-2018.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private ArrayList<Game> games;
    private BannerAdapter.ClickedListener clickedListener;

    public GameAdapter(Context context, ArrayList<Game> games) {
        this.games = games;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_row, parent, false);
        return new GameViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        final Game game = games.get(position);
        holder.image.setImageResource(game.getImageId());
        holder.text_game_name.setText(game.getGameName());
        holder.text_company.setText(game.getCompanyName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedListener.onClick(game);
            }
        });
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView text_game_name;
        final TextView text_company;

        public GameViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text_game_name = (TextView) itemView.findViewById(R.id.text_game_name);
            text_company = (TextView) itemView.findViewById(R.id.text_company_name);
        }
    }

    public void setListener(BannerAdapter.ClickedListener clickedListener){
        this.clickedListener=clickedListener;
    }
    public interface ClickedListener{
        public void onClick(Game game);
    }
}
