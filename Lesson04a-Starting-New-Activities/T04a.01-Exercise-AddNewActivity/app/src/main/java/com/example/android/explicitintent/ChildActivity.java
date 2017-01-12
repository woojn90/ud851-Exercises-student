package com.example.android.explicitintent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lg on 2017-01-04.
 */

public class ChildActivity extends AppCompatActivity {
    private TextView mDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplay = (TextView) findViewById(R.id.tv_display);
    }
}
