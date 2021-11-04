//package com.zecho.countmatic;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//
//public class MainActivity extends AppCompatActivity {
//    private int step = 1;
//    private int count = 0;
//    private Operation op;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.constraint);
//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//
//        /////////////////////////////////////////////////////////////////////////////////////////
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        /////////////////////////////////////////////////////////////////////////////////////////
//
//        //Declaration of Variables
//        Button btnCount = findViewById(R.id.btn_count);
//        Button btnReset = findViewById(R.id.btn_reset);
//        Button btnDown = findViewById(R.id.btn_down);
//        Button btnUp = findViewById(R.id.btn_up);
//        TextView tvCount = findViewById(R.id.tv_display);
//        TextView tvSteps = findViewById(R.id.tv_steps_count);
//        op = new Operation();
//
//        //Codes
//        btnCount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (count < 99999) {
//                    count = count + step;
//                }
//                //tvCount.setText(String.format("%04d.", count));
//                op.changeTally(tvCount, count);
//            }
//        });
//
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count = 0;
//                step = 1;
//                op.changeTally(tvCount, count);
//                op.changeStep(tvSteps, step);
//            }
//        });
//
//        btnUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                step++;
//                op.changeStep(tvSteps, step);
//            }
//        });
//
//        btnDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (step > 1) {
//                    step--;
//                    op.changeStep(tvSteps, step);
//                }
//            }
//        });
//
//    }//onCreate
//}//Main