package com.cs407.hippie_christmas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    ArrayList<Items> itemList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    PostDatabaseHelper postDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

       itemList = new ArrayList<>();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner mySpinner = findViewById(R.id.pick_category);
        mySpinner.setAdapter(adapter);

        ArrayList<String> displayCategories = new ArrayList<>();

        postDB = new PostDatabaseHelper(this);


        // Call the readPosts method to get a list of items
        itemList = postDB.readPosts();
        // Display the categories on the screen
        for (Items items : itemList) {
            displayCategories.add(items.getTitle().toString());
        }
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayCategories);
        ListView categoryListView = (ListView) findViewById(R.id.categoryList);
        categoryListView.setAdapter(adapter1);

        // do something for when a category is selected
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), Results.class);
//                intent.putExtra("category", i);
//                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.categories);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            int id = item.getItemId();
            if(id == R.id.home){
                Intent intent1 = new Intent(Categories.this, home_page.class);
                startActivity(intent1);
                return true;
            } else if (id == R.id.categories) {
                Intent intent2 = new Intent(Categories.this, Categories.class);
                startActivity(intent2);
                return true;
            } else if (id == R.id.post){
                Intent intent3 = new Intent(Categories.this, new_post.class);
                startActivity(intent3);
                return true;
            }

            return false;
        });
    }
}