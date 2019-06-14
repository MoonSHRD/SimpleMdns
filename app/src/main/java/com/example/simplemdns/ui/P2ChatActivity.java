package com.example.simplemdns.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemdns.P2ChatService;
import com.example.simplemdns.R;
import com.example.simplemdns.models.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class P2ChatActivity extends AppCompatActivity {

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
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onNewMessage(Message message) {
        Toast.makeText(this, message.text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
