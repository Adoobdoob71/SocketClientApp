package com.eladnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.eladnet.ServiceHandling.Adapters.MessageAdapter;
import com.eladnet.ServiceHandling.Message;
import com.eladnet.ServiceHandling.Threads.ChatThread;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {


    ListView listView;
    MaterialToolbar toolbar;
    EditText editText;
    ArrayList<Message> arrayList = new ArrayList<>();
    MessageAdapter messageAdapter;
    ChatThread chatThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        InitializeVariables();
        HandleEvents();
    }

    public void InitializeVariables(){
        listView = findViewById(R.id.activity_chat_list_view);
        toolbar = findViewById(R.id.activity_chat_toolbar);
        editText = findViewById(R.id.activity_chat_edit_text);
        messageAdapter = new MessageAdapter(this, arrayList);
        listView.setAdapter(messageAdapter);
        chatThread = new ChatThread(MainActivity.socket);
        chatThread.start();
    }

    public void HandleEvents(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatThread.destroy();
                finish();
            }
        });
    }

    public void SendMessage(View view) {
        try {
            ChatThread.sendMessageToServer(editText.getText().toString());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}