package com.jiotask.nikhil.jiotask.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiotask.nikhil.jiotask.R;
import com.jiotask.nikhil.jiotask.model.NavigationMenuItem;

import java.util.List;

/**
 * Created by Nikhil on 12-05-2018.
 */

public class NavigationAdapter extends BaseAdapter {

    private final List<NavigationMenuItem> mNavigationItems;
    private final Context mContext;


    public NavigationAdapter(Context context,List<NavigationMenuItem> navigationItems){
        this.mContext=context;
        this.mNavigationItems=navigationItems;
    }
    @Override
    public int getCount() {
        return mNavigationItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mNavigationItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.navigation_menu_item, null);
        }

        NavigationMenuItem navigationMenuItem = mNavigationItems.get(position);

        TextView txtView = (TextView)v.findViewById(R.id.txtView);

        ImageView image = (ImageView) v.findViewById(R.id.imageView);

        txtView.setText(navigationMenuItem.getName());
        Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(), navigationMenuItem.getImageId());
        image.setImageBitmap(bitmap);

        return v;
    }
}
