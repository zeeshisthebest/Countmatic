package com.zecho.countmatic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updated);

        RecyclerView rvSaves = findViewById(R.id.rv_saved);

        rvSaves.setAdapter(new SaveAdapter(this, getMap()));

        rvSaves.setLayoutManager(new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false));
    }


    public HashMap<String, Integer> getMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("First", 1);
        map.put("Second", 2);
        map.put("Third", 3);
        map.put("Fourth", 4);
        map.put("Fifth", 5);
        map.put("Sixth", 6);
        map.put("Seventh", 7);

        return map;
    }
}

