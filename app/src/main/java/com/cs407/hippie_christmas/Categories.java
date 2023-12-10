package com.cs407.hippie_christmas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    ArrayList<Items> itemList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    PostDatabaseHelper postDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner mySpinner = findViewById(R.id.pick_category);
        mySpinner.setAdapter(adapter);

        ArrayList<String> displayCategories = new ArrayList<>();

        postDB = new PostDatabaseHelper(this);

        // Call the readPosts method to get a list of items
        itemList = postDB.readPosts();
        updateListView(displayCategories);


        //Listener for the spinner
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                if ("No Category".equals(selectedCategory)) {
                    itemList = postDB.readPosts();
                } else {
                    itemList = postDB.readPostsByCategory(selectedCategory);
                }
                updateListItems(itemList, displayCategories);
                updateListView(displayCategories);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                itemList = postDB.readPosts();
                updateListItems(itemList, displayCategories);
                updateListView(displayCategories);
            }

        });


        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayCategories);
           ListView categoryListView = (ListView) findViewById(R.id.categoryList);
        categoryListView.setAdapter(adapter1);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // do something for when a category is selected
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void updateListView(ArrayList<String> displayCategories) {
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayCategories);
        ListView categoryListView = (ListView) findViewById(R.id.categoryList);
        categoryListView.setAdapter(adapter1);
    }

    private void updateListItems(ArrayList<Items> itemList, ArrayList<String> displayCategories) {
        displayCategories.clear();
        itemList.clear();
        itemList.addAll(postDB.readPosts());
        for (Items items : itemList) {
            displayCategories.add(items.getTitle().toString());
        }
    }
}