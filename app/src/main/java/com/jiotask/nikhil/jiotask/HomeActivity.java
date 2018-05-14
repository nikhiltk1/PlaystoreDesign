package com.jiotask.nikhil.jiotask;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jiotask.nikhil.jiotask.adapter.NavigationAdapter;
import com.jiotask.nikhil.jiotask.model.NavigationMenuItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ListView navList;
    private List<NavigationMenuItem> navItems;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationAdapter navAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        navList = (ListView) findViewById(R.id.navlist);
        prepareListData();

        navAdapter = new NavigationAdapter(this, navItems);

        // setting list adapter
        navList.setAdapter(navAdapter);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //listViewMenu=(ListView)findViewById(R.id.listViewMenu);

        drawerLayout.addDrawerListener(new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();

            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);/*
        llToolbarActivity=(LinearLayout)toolbar.findViewById(R.id.ll_activity);
        llToolbarFragment=(LinearLayout)toolbar.findViewById(R.id.ll_fragment);
        imageSearchIcon=(ImageView)toolbar.findViewById(R.id.image_search_icon);
        imageSearchClear = (ImageView) toolbar.findViewById(R.id.image_search_clear);
        editSearch=(EditText)toolbar.findViewById(R.id.edit_search) ;
        textTitle = (TextView) toolbar.findViewById(R.id.text_title);
        textToolbar=(TextView)toolbar.findViewById(R.id.text_toolbar_title);*/

        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View navigationHeader = navigationView.getHeaderView(0);
        TextView lblName = (TextView) navigationHeader.findViewById(R.id.lblName);
        lblName.setText("Name");

        TextView lblEmail = (TextView) navigationHeader.findViewById(R.id.lblEmail);
        lblEmail.setText("Email");


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();

    }

    private void prepareListData() {
        navItems = new ArrayList<>();
        navItems.add(new NavigationMenuItem("Home", R.drawable.homeicon));
    }

}
