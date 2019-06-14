package com.example.simplemdns;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.simplemdns.models.Message;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import p2mobile.P2mobile;

public class P2ChatService extends Service {
    private final static String LOG_TAG = "P2ChatService";

    private P2ChatServiceBinder binder = new P2ChatServiceBinder();
    private ScheduledExecutorService scheduledExecutorService;

    public P2ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onServiceStart();
        return Service.START_NOT_STICKY;
    }

    private void onServiceStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        new Thread(() -> P2mobile.start()).start();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            AppHelper.streamApi = P2mobile.getStreamApi();

            if(AppHelper.streamApi != null) {
                String messageText = P2mobile.streamReader(AppHelper.streamApi);
                if(!messageText.equals("")) {
                    EventBus.getDefault().post(new Message(messageText));
                    Log.d(LOG_TAG, "New message: " + messageText);
                } else {
                    Log.d(LOG_TAG, "No peers found yet.");
                }
            }
        }, 3, 2, TimeUnit.SECONDS);
    }

    public class P2ChatServiceBinder extends Binder {
        public P2ChatService getService() {
            return P2ChatService.this;
        }
    }
}