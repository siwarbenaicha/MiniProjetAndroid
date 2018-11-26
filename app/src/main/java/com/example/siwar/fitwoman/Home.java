package com.example.siwar.fitwoman;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.example.siwar.fitwoman.fragment.ActivitiesFragment;
import com.example.siwar.fitwoman.fragment.DietFragment;
import com.example.siwar.fitwoman.fragment.FitnessFragment;
import com.example.siwar.fitwoman.fragment.HomeFragment;
import com.example.siwar.fitwoman.fragment.MealsFragment;
import com.example.siwar.fitwoman.fragment.ProfilFragment;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar ,R.string.navigation_drawer_open ,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //copy and paste the same case for all the rest of the menu
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
               // getSupportActionBar().setTitle("Home"); BCS DON T WORK
                break;
            case R.id.nav_fitness:
               // startActivity(new Intent(getApplicationContext(), MarwaFitness.class));
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FitnessFragment()).commit();

                getSupportActionBar().setTitle("Fitness Program");

                break;
            case R.id.nav_activity:
               // startActivity(new Intent(getApplicationContext(), MarwaAdd.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ActivitiesFragment()).commit();
                getSupportActionBar().setTitle("Daily Activities");
                break;
            case R.id.nav_diet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DietFragment()).commit();
                getSupportActionBar().setTitle("Diet Program");
                break;
            case R.id.nav_meals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MealsFragment()).commit();
                getSupportActionBar().setTitle("Daily Meals");
                break;
            case R.id.nav_profil:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
                getSupportActionBar().setTitle("Profil");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);



        }else{
            super.onBackPressed();
        }

    }


    ///////////////////


}
