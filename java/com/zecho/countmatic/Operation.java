package com.zecho.countmatic;

import android.widget.TextView;

import java.util.Locale;

public class Operation {

    public void changeTally(TextView tv, int digit) {
        tv.setText(String.format(Locale.getDefault(), "%04d.", digit));
    }

    public void changeStep(TextView tv, int value) {
        tv.setText(String.format(Locale.getDefault(), "%02d.", value));
    }
}
