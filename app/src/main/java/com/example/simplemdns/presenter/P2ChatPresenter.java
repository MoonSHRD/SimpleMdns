package com.example.simplemdns.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.simplemdns.AppHelper;
import com.example.simplemdns.contracts.P2ChatUI;
import com.example.simplemdns.models.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@InjectViewState
public class P2ChatPresenter extends MvpPresenter<P2ChatUI> {
    public P2ChatPresenter() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onNewMessage(Message message) {
        getViewState().addLog(message.from + " > " + message.body);
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
