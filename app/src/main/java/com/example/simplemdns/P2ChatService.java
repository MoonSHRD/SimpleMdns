package com.example.simplemdns;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import p2mobile.P2mobile;

public class P2ChatService extends Service {
    private P2ChatServiceBinder binder = new P2ChatServiceBinder();

    public P2ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onServiceStart();
        return Service.START_STICKY;
    }

    private void onServiceStart() {
        new Thread(P2mobile::start).start();
    }

    public class P2ChatServiceBinder extends Binder {
        public P2ChatService getService() {
            return P2ChatService.this;
        }
    }
}
