package com.zecho.countmatic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, SaveDialog.NoticeDialogListener {

    public static String PREFERENCE_KEY = "zecho.countmatic.SAVES";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView tvName, tvSteps, tvCount;
    Button btnSave, btnIncrease, btnDecrease, btnCount;
    ArrayList<String> keySets;
    int count, step;
    RecyclerView rvSaves;
    SaveAdapter saveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updated);

        initialize();

        rvSaves = findViewById(R.id.rv_saved);

        saveAdapter = new SaveAdapter(this);
        saveAdapter.setItems(keySets);
        rvSaves.setAdapter(saveAdapter);

        rvSaves.setLayoutManager(new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false));
    }


    public void saveValue(HashMap<String, Integer> tally){

    }

    public void initialize(){
        sp = this.getSharedPreferences(PREFERENCE_KEY, 0);

        editor = sp.edit();

        keySets = getKeys();

        count = 0;

        step = 1;

        tvName = findViewById(R.id.tv_tally_name);

        tvCount = findViewById(R.id.tv_tally);

        tvSteps = findViewById(R.id.tv_steps);

        btnCount = findViewById(R.id.btn_count);
        btnCount.setOnClickListener(this);

        btnDecrease = findViewById(R.id.btn_decrease);
        btnDecrease.setOnClickListener(this);

        btnIncrease = findViewById(R.id.btn_increase);
        btnIncrease.setOnClickListener(this);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_count:
                count += step;
                tvCount.setText(String.format("%05d.", count));
                break;

            case R.id.btn_decrease:
                String minus = "";

                if (step > 1) {
                    minus = "Steps: " + (--step);
                } else {
                    minus = "Steps: 1";
                }

                tvSteps.setText(minus);

                break;

            case R.id.btn_increase:
                String sPlus = "Steps: " + (++step);
                tvSteps.setText(sPlus);
                break;

            case R.id.btn_save:
                SaveDialog saveDialog = new SaveDialog();
                saveDialog.show(getSupportFragmentManager(), "Save");
                break;
        }

    }

    @Override
    public void onDialogPositiveClick(String name) {
        editor.putInt(name, count);
        Toast.makeText(this, "Called me", Toast.LENGTH_SHORT).show();
        editor.apply();
        saveAdapter.setItems(getKeys());
        saveAdapter.notifyDataSetChanged();
    }

    public ArrayList<String> getKeys(){
        return new ArrayList<>(sp.getAll().keySet());
    }
    
}

