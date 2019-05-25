package com.example.simplemdns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import p2mobile.P2mobile;

public class p2chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2chat);
        P2mobile.start();

    }
}
