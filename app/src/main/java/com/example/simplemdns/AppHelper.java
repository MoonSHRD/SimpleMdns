package com.example.simplemdns;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class AppHelper extends Application {
    public static final String SERVICE_TOPIC = "moonshard";
    public static final String PROTOCOL_ID = "/moonshard/1.0.0";

    public static ServiceConnection serviceConnection = null;
    public static P2ChatService service = null;
    public static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        AppHelper.context.startService(new Intent(AppHelper.context, P2ChatService.class));
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                AppHelper.serviceConnection = this;
                AppHelper.service = ((P2ChatService.P2ChatServiceBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                AppHelper.serviceConnection = null;
                AppHelper.service = null;
            }
        };
        AppHelper.context.bindService(new Intent(AppHelper.context, P2ChatService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
