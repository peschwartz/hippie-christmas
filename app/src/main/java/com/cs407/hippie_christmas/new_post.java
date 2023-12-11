package com.cs407.hippie_christmas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.atomic.AtomicReference;

public class new_post extends AppCompatActivity implements OnMapReadyCallback {
    private String category;
    EditText location;
    EditText title;
    BottomNavigationView bottomNavigationView;
    PostDatabaseHelper postDB;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;

    String locationString = "0,0";

    private FusedLocationProviderClient mFusedLocationProviderClient;
// TODO this code is spaghetti. needs to be organized
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //boilerplate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner mySpinner = findViewById(R.id.new_item_category);
        mySpinner.setAdapter(adapter);

        title = findViewById(R.id.new_item_title);
        location = findViewById(R.id.new_item_loc);
        postDB = new PostDatabaseHelper(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



        //TODO: implement a map view with a default location of the user's current location
        MapView mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(this);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                // Handle the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = parent.getItemAtPosition(0).toString();
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


        Button buttonCreatePost = findViewById(R.id.buttonCreatePost);

        buttonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserLocation();
                String itemLocation = locationString;
                String itemTitle = title.getText().toString();


                if (itemLocation.isEmpty()) {
                    itemLocation = "user_loc";
                }

                // TODO create and implement missing fields
                postDB.addPost("default_user",
                        itemTitle,
                        itemLocation,
                        category,
                        "default_img_path");

                Toast.makeText(
                        new_post.this,
                        "Post created successfully",
                        Toast.LENGTH_SHORT)
                        .show();
                openMainScreen();

            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng loc = new LatLng(43.072, 89.4098);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

    }

    public void openMainScreen() {
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    private void getUserLocation(){
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        int permission = ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        else {
            mFusedLocationProviderClient.getLastLocation()
                    .addOnCompleteListener(  this, task -> {
                        Location mLastKnownLocation = task.getResult();
                        if (task.isSuccessful() && mLastKnownLocation != null) {
                            String latitude = Location.convert(mLastKnownLocation.getLatitude(), Location.FORMAT_SECONDS);
                            String longitude = Location.convert(mLastKnownLocation.getLongitude(), Location.FORMAT_SECONDS);
                            locationString = latitude + ","+longitude;
                        }
                    });

        }
    }


}
