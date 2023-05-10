package com.example.networking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<RecyclerViewItem> items = new ArrayList<>(Arrays.asList(
                new RecyclerViewItem("Matterhorn"),
                new RecyclerViewItem("Mont Blanc"),
                new RecyclerViewItem("Denali")
        ));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, item -> Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show());

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);


        new JsonTask(this, this).execute(JSON_URL);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Mountain[] mountains = gson.fromJson(json, Mountain[].class);

        ArrayList<RecyclerViewItem> items = new ArrayList<>();

        for (Mountain mountain : mountains) {
            items.add(new RecyclerViewItem(mountain.toString()));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, item -> Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show());

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

}