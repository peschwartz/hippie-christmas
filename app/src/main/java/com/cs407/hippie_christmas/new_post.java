package com.cs407.hippie_christmas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class new_post extends AppCompatActivity implements OnMapReadyCallback {
    String title, location, condition;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //boilerplate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //gathering of
        Spinner mySpinner = findViewById(R.id.new_item_category);
        mySpinner.setAdapter(adapter);
        title = findViewById(R.id.new_item_title).toString();
        location = findViewById(R.id.new_item_loc).toString();


        //TODO: implement a map view with a default location of the user's current location
        MapView mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(this);

        // Item Category Selector todo: refactor to reflect this
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                condition = parent.getItemAtPosition(position).toString();
                // Handle the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        // Bottom Navigation View Boilerplate
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.post);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            int id = item.getItemId();
            if(id == R.id.home){
                Intent intent1 = new Intent(new_post.this, home_page.class);
                startActivity(intent1);
                return true;
            } else if (id == R.id.categories) {
                Intent intent2 = new Intent(new_post.this, Categories.class);
                startActivity(intent2);
                return true;
            } else if (id == R.id.post){
                Intent intent3 = new Intent(new_post.this, new_post.class);
                startActivity(intent3);
                return true;
            }

            return false;
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng loc = new LatLng(43.072, 89.4098);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

    }
}
