package com.example.simplemdns.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.simplemdns.AppHelper;
import com.example.simplemdns.P2ChatService;
import com.example.simplemdns.contracts.P2ChatUI;
import com.example.simplemdns.models.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@InjectViewState
public class P2ChatPresenter extends MvpPresenter<P2ChatUI> {
    public P2ChatPresenter() {
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
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onNewMessage(Message message) {
        getViewState().addLog("Remote > " + message.text);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void sendMessage(String text) {
        new Thread(() -> AppHelper.service.sendMessage(text)).start();
        getViewState().addLog("You > " + text + "\n");
    }
}
