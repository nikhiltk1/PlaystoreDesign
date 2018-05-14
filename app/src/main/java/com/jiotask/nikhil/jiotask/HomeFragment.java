package com.jiotask.nikhil.jiotask;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiotask.nikhil.jiotask.adapter.BannerAdapter;
import com.jiotask.nikhil.jiotask.adapter.GameAdapter;
import com.jiotask.nikhil.jiotask.model.Game;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends android.support.v4.app.Fragment implements BannerAdapter.ClickedListener,GameAdapter.ClickedListener {

    RecyclerView recycler_banner;
    RecyclerView recycler_recommended;
    RecyclerView recycler_trending;
    ArrayList<Game> bannerGames=new ArrayList<>();
    ArrayList<Game> trendingGames=new ArrayList<>();
    ArrayList<Game> recommendedGames=new ArrayList<>();
    BottomSheetDialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recycler_banner=(RecyclerView)root.findViewById(R.id.recycler_banner);
        recycler_trending=(RecyclerView)root.findViewById(R.id.recycler_trending);
        recycler_recommended=(RecyclerView)root.findViewById(R.id.recycler_recommended);
        loadData();


        BannerAdapter bannerAdapter=new BannerAdapter(getContext(),bannerGames);
        bannerAdapter.setListener(this);
        RecyclerView.LayoutManager layoutManagerHorizontal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_banner.setItemAnimator(new DefaultItemAnimator());
        recycler_banner.setLayoutManager(layoutManagerHorizontal);
        recycler_banner.setHasFixedSize(true);
        recycler_banner.setAdapter(bannerAdapter);

        mDialog = new BottomSheetDialog(getContext());
        GameAdapter recommendedAdapter=new GameAdapter(getContext(),recommendedGames);
        RecyclerView.LayoutManager layoutManagerHorizontalRecom = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_recommended.setItemAnimator(new DefaultItemAnimator());
        recycler_recommended.setLayoutManager(layoutManagerHorizontalRecom);
        recycler_recommended.setHasFixedSize(true);
        recycler_recommended.setAdapter(recommendedAdapter);
        recommendedAdapter.setListener(this);

        GameAdapter trendingAdapter=new GameAdapter(getContext(),trendingGames);
        RecyclerView.LayoutManager layoutManagerHorizontalTrend = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_trending.setItemAnimator(new DefaultItemAnimator());
        recycler_trending.setLayoutManager(layoutManagerHorizontalTrend);
        recycler_trending.setHasFixedSize(true);
        recycler_trending.setAdapter(trendingAdapter);
        trendingAdapter.setListener(this);

        return root;
    }
    private void showBottomView(Game game){
        View view=LayoutInflater.from(getContext())
                .inflate(R.layout.popup, null, false);
        view.setBackgroundColor(getContext().getColor(R.color.color_trans_white));
        RatingBar ratingBar=view.findViewById(R.id.rating);
        ImageView imageView=view.findViewById(R.id.image);
        TextView text_desc=view.findViewById(R.id.text_desc);
        ratingBar.setRating(game.getRating());
        text_desc.setText(game.getCompanyName());
        imageView.setImageResource(game.getImageId());
        mDialog.setContentView(view);

        mDialog.show();
    }
    private void hideBottomView(){
        mDialog.hide();
    }
    private void loadData(){
        JSONObject jsonObject=null;
        try {
            Type listType = new TypeToken<List<Game>>() {
            }.getType();
            jsonObject = new JSONObject("{\"banner\":[{\"gameName\":\"Clash of clans\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Jio\",\"imageId\":"+R.drawable.clash_of_clans+",\"rating\" : 4},{\"gameName\":\"Temple Run\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Temple run company\",\"imageId\":"+R.drawable.clash_of_clans+",\"rating\" : 1},{\"gameName\":\"Subway surfers\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.clash_of_clans+",\"rating\" : 2}],\"recommended\":[{\"gameName\":\"Farmville2\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.farmville+",\"rating\" : 1},{\"gameName\":\"Lets Farm\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.farmville+",\"rating\" : 5},{\"gameName\":\"Hay Day\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.farmville+",\"rating\" : 1}],\"trending\":[{\"gameName\":\"Clash of clans\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.farmville+",\"rating\" : 1},{\"gameName\":\"Clash of clans\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.farmville+",\"rating\" : 1},{\"gameName\":\"Clash of clans\",\"imageUrl\":\"Clash of clans\",\"companyName\":\"Company name\",\"imageId\":"+R.drawable.farmville+",\"rating\" : 4}]}");
            ArrayList<Game> bannerList = new Gson().fromJson(jsonObject.getJSONArray("banner").toString(), listType);
            bannerGames.addAll(bannerList);

            ArrayList<Game> recommendedList = new Gson().fromJson(jsonObject.getJSONArray("recommended").toString(), listType);
            recommendedGames.addAll(recommendedList);

            ArrayList<Game> trendingList = new Gson().fromJson(jsonObject.getJSONArray("trending").toString(), listType);
            trendingGames.addAll(trendingList);

        }catch (JSONException e){e.printStackTrace();}
        int a=R.drawable.app_logo;

        Log.v("","a"+a);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(Game game) {
        showBottomView(game);
    }
}
