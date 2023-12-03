package com.cs407.hippie_christmas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    ArrayList<Items> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // list all of the different items to choose from
        // read results from the db = dbHelper.readNotes(userName);

        ArrayList<String> displayCategories = new ArrayList<>();

        for (Items category: itemList ){
            displayCategories.add(String.format(category.getCategory()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayCategories);
        ListView itemListView = (ListView) findViewById(R.id.itemListPage);
        itemListView.setAdapter(adapter);

        // do something for when a category is selected
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Results.class);
                intent.putExtra("category", i);
                startActivity(intent);
            }
        });
    }
}