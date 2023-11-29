package com.cs407.hippie_christmas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Categories extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    ArrayList<Items> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // list all of the different categories to choose from
        // initialize the "notes" class variable using readNotes in the DBHelper class. Use the username from SharedPreferences as a parameter
        // read categories from the db = dbHelper.readItems();

        ArrayList<String> displayCategories = new ArrayList<>();

        for (Items items: itemList){
            displayCategories.add(items.getCategory());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayCategories);
        ListView categoryListView = (ListView) findViewById(R.id.categoryList);
        categoryListView.setAdapter(adapter);

        // do something for when a category is selected
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Results.class);
                intent.putExtra("category", i);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.categories);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Intent intent;
//
//        int itemId = item.getItemId();
//        if (itemId == R.id.home) {
//            intent = new Intent(Categories.this, home_page.class);
//            startActivity(intent);
//            finish();
//            return true;
//        } else if (itemId == R.id.categories) {
//            intent = new Intent(Categories.this, Categories.class);
//            startActivity(intent);
//            finish();
//            return true;
//        } else if (itemId == R.id.post) {
//            intent = new Intent(Categories.this, new_post.class);
//            startActivity(intent);
//            finish();
//            return true;
//        }
        return false;

    }
}