package com.cs407.hippie_christmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class home_page extends AppCompatActivity implements OnMapReadyCallback {

    BottomNavigationView bottomNavigationView;
    private GoogleMap mMap;
    PostDatabaseHelper postDB;
    ArrayList<Items> itemList = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<Items> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);


        listView = findViewById(androidx.appcompat.R.id.scrollView);

        ArrayList <String> displayCategories = new ArrayList<>();

        postDB = new PostDatabaseHelper(this);

        itemList = postDB.readPosts();

        displayCategories.clear();
        for (Items items : itemList) {
            displayCategories.add(items.getTitle().toString());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayCategories);
        listView.setAdapter(adapter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedTitle = displayCategories.get(i);

                Items selectedItem = findItemByTitle(selectedTitle);

                if(selectedItem != null) {
                    String title = selectedItem.getTitle();
                    String location = selectedItem.getLocation();
                    String category = selectedItem.getCategory();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.fragmentContainer, ItemFragment.newInstance(title, location, category));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }

            private Items findItemByTitle(String title) {
                for (Items item: itemList) {
                    if (item.getTitle().equals(title)) {
                        return item;
                    }
                }
                return null;
            }
        });

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