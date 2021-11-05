package com.zecho.countmatic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, SaveDialog.NoticeDialogListener {

    public static String PREFERENCE_KEY = "zecho.countmatic.SAVES";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView tvName, tvSteps, tvCount, tvIfEmpty;
    Button btnSave, btnIncrease, btnDecrease, btnCount;
    int count, step;
    RecyclerView rvSaves;
    SaveAdapter saveAdapter;
    MainActivity2 ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.updated);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
                        @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        initialize();

        ma = this;

        rvSaves = findViewById(R.id.rv_saved);

        saveAdapter = new SaveAdapter(this, ma);
        saveAdapter.setItems(getKeys());
        rvSaves.setAdapter(saveAdapter);

        rvSaves.setLayoutManager(new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false));
        manageViews();
    }


    public void saveValue(HashMap<String, Integer> tally) {

    }

    public void initialize() {

        sp = this.getSharedPreferences(PREFERENCE_KEY, 0);

        editor = sp.edit();

        count = 0;

        step = 1;

        tvName = findViewById(R.id.tv_tally_name);

        tvCount = findViewById(R.id.tv_tally);

        tvSteps = findViewById(R.id.tv_steps);

        tvIfEmpty = findViewById(R.id.tv_if_empty);

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
        switch (v.getId()) {
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
        editor.apply();
        saveAdapter.setItems(getKeys());
        saveAdapter.notifyDataSetChanged();
        manageViews();
        reset(0);
    }

    public ArrayList<String> getKeys() {
        return new ArrayList<>(sp.getAll().keySet());
    }

    /**
     * Resets the counter
     *
     * @param count to set the initial count
     */
    public void reset(int count) {
        this.count = count;
        this.step = 1;
        tvCount.setText(String.format("%05d.", count));
        tvSteps.setText("Steps: 1");
    }


    /**
     * Called when the user taps stored counts
     *
     * @param name  name of the counter to be loaded
     * @param count stored count of it
     */
    public void loadSavedCount(String name, int count) {

        tvName.setText(name);
        reset(count);
    }

    /**
     *  Shows or Hide the empty list banner
     */
    public void manageViews(){
        if(getKeys().size() == 0){
            rvSaves.setVisibility(View.GONE);
            tvIfEmpty.setVisibility(View.VISIBLE);
        } else {
            rvSaves.setVisibility(View.VISIBLE);
            tvIfEmpty.setVisibility(View.GONE);
        }
    }

}

