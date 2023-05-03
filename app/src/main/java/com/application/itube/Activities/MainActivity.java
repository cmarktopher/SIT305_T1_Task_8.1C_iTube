package com.application.itube.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.application.itube.R;

/**
 * For this task, I took the opportunity to learn how to use the navigation graph,
 * as it looked really useful for handling transitions.
 * https://developer.android.com/guide/navigation/navigation-getting-started
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}