package com.example.simplemdns;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import p2mobile.P2mobile;

public class P2ChatService extends Service {
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
        new Thread(() -> {
            P2mobile.start();
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                AppHelper.streamApi = P2mobile.getP();
            }, 0, 2, TimeUnit.SECONDS);
        }).start();
    }

    public class P2ChatServiceBinder extends Binder {
        public P2ChatService getService() {
            return P2ChatService.this;
        }
    }

    /*@Override
    public boolean onUnbind(Intent intent) {
        stopSelf();
        return false;
    }*/
}