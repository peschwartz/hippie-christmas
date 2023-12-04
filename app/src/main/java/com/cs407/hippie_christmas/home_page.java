package com.cs407.hippie_christmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_page extends AppCompatActivity implements OnMapReadyCallback {

    BottomNavigationView bottomNavigationView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                Intent intent1 = new Intent(home_page.this, home_page.class);
                startActivity(intent1);
                return true;
            } else if (id == R.id.categories) {
                Intent intent2 = new Intent(home_page.this, Categories.class);
                startActivity(intent2);
                return true;
            } else if (id == R.id.post) {
                Intent intent3 = new Intent(home_page.this, new_post.class);
                startActivity(intent3);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng lat = new LatLng(43.0722, 89.4008);
        mMap.moveCamera (CameraUpdateFactory.newLatLng(lat));
    }
}