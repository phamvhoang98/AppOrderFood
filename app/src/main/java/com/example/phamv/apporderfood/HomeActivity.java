package com.example.phamv.apporderfood;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.phamv.apporderfood.FragmentApp.ShowTablesFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar  toolbar;
    TextView txt_employee_navigation;
    android.app.FragmentManager fragmentManager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationViewHome);
        toolbar = findViewById(R.id.toolbar);

//        View view = LayoutInflater.from(this).inflate(R.layout.layout_header_navigation_home,null);
        View view = navigationView.inflateHeaderView(R.layout.layout_header_navigation_home);
        txt_employee_navigation = view.findViewById(R.id.txtNameEmployee_navigation);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("tendangnhap");
//        Log.d("dulieu" ,tendangnhap);
        txt_employee_navigation.setText(username);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragTranShowTables = fragmentManager.beginTransaction();
        ShowTablesFragment showTablesFragment = new ShowTablesFragment();
        fragTranShowTables.replace(R.id.content,showTablesFragment);
        fragTranShowTables.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ithome:
                FragmentTransaction fragTranShowTables = fragmentManager.beginTransaction();
                ShowTablesFragment showTablesFragment = new ShowTablesFragment();
                fragTranShowTables.replace(R.id.content,showTablesFragment);
                fragTranShowTables.commit();

                item.setChecked(true);
                drawerLayout.closeDrawer(0);
                break;

            case R.id.itemployee:
        }

        return false;
    }
}
