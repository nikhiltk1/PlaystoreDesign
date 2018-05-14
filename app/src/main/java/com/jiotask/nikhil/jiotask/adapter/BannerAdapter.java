package com.jiotask.nikhil.jiotask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiotask.nikhil.jiotask.HomeFragment;
import com.jiotask.nikhil.jiotask.R;
import com.jiotask.nikhil.jiotask.model.Game;

import java.util.ArrayList;

/**
 * Created by Nikhil on 12-05-2018.
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private ArrayList<Game> games;
    private Context mContext;
    private ClickedListener clickedListener;

    public BannerAdapter(Context context, ArrayList<Game> games) {
        this.games = games;
        this.mContext=context;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_row, parent, false);

        return new BannerViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        final Game game = games.get(position);
        holder.imageBanner.setImageResource(game.getImageId());
        if(holder.imageBanner.isFocused()){

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
//width and height of your Image ,if it is inside Relative change the LinearLayout to RelativeLayout.
            holder.imageBanner.setLayoutParams(layoutParams);
        }
        holder.imageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedListener.onClick(game);
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageBanner;

        public BannerViewHolder(final View itemView) {
            super(itemView);
            imageBanner = (ImageView) itemView.findViewById(R.id.image_setting);

            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // run scale animation and make it bigger
                        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.scale_in_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);
                    } else {
                        // run scale animation and make it smaller
                        Animation anim = AnimationUtils.loadAnimation(mContext , R.anim.scale_out_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);

                    }
                }
            });
        }
    }
    public void setListener(ClickedListener clickedListener){
        this.clickedListener=clickedListener;
    }
    public interface ClickedListener{
        public void onClick(Game game);
    }

}
