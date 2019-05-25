package com.example.simplemdns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import p2mobile.P2mobile;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startChat (View view) {

        Intent intent = new Intent(this,p2chat.class );
        startActivity(intent);


    }
}
