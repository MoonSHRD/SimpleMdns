package com.example.simplemdns;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

public class p2chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2chat);
        startService(new Intent(this, P2ChatService.class));
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // TODO
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // TODO
            }
        };
        bindService(new Intent(this, P2ChatService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
