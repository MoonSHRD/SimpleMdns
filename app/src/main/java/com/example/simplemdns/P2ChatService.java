package com.example.simplemdns;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.simplemdns.models.Message;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pkg.Pkg;

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
        final Gson gson = new Gson();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        new Thread(() -> pkg.Pkg.start(AppHelper.SERVICE_TOPIC, AppHelper.PROTOCOL_ID, "", 0)).start();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            String message = Pkg.getMessages();
            if(!message.isEmpty()) {
                Message messageObject = gson.fromJson(message, Message.class);
                EventBus.getDefault().post(messageObject);
                Log.d(LOG_TAG, "New message! " + messageObject.from + " > " + messageObject.body);
            }
        }, 0, 300, TimeUnit.MILLISECONDS);
    }

    public class P2ChatServiceBinder extends Binder {
        public P2ChatService getService() {
            return P2ChatService.this;
        }
    }

    public void sendMessage(String text) {
        Pkg.publishMessage(AppHelper.SERVICE_TOPIC, text + "\n");
    }
}