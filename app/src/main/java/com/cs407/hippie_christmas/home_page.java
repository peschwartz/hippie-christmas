package com.cs407.hippie_christmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_page extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
//        mapFragment.getMapAsync(googleMap ->{
//            mMap = googleMap;
//        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener((item) -> {
            int id = item.getItemId();
            if(id == R.id.home){
                Intent intent1 = new Intent(home_page.this, home_page.class);
                startActivity(intent1);
                return true;
            } else if (id == R.id.categories) {
                Intent intent2 = new Intent(home_page.this, Categories.class);
                startActivity(intent2);
                return true;
            } else if (id == R.id.post){
                Intent intent3 = new Intent(home_page.this, new_post.class);
                startActivity(intent3);
                return true;
            }

            return false;
        });
    }
}
