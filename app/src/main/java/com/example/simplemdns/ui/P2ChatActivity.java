package com.example.simplemdns.ui;

import android.os.Bundle;
import android.widget.EditText;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.simplemdns.AppHelper;
import com.example.simplemdns.R;
import com.example.simplemdns.contracts.P2ChatUI;
import com.example.simplemdns.presenter.P2ChatPresenter;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class P2ChatActivity extends MvpActivity implements P2ChatUI {
    @InjectPresenter
    P2ChatPresenter presenter;

    @BindView(R.id.log_view)
    EditText logView;

    @BindView(R.id.message_input)
    EditText messageInput;

    @BindView(R.id.send_button)
    MaterialButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2chat);
        ButterKnife.bind(this);
        sendButton.setOnClickListener((v) -> {
            presenter.sendMessage(messageInput.getText().toString());
            messageInput.setText("");
        });
    }

    @Override
    public void addLog(String text) {
        logView.append(text);
    }
}
