package com.example.simplemdns;

import android.app.Application;
import android.content.Context;
import android.content.ServiceConnection;

import p2mobile.StreamApi;

public class AppHelper extends Application {
    public static StreamApi streamApi = null;
    public static ServiceConnection serviceConnection = null;
    public static P2ChatService service = null;
    public static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
