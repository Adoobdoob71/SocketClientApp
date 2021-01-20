package com.eladnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eladnet.ServiceHandling.Adapters.UserAdapter;
import com.eladnet.ServiceHandling.Threads.ClientThread;
import com.eladnet.ServiceHandling.UserSocket;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    ListView listView;
    ArrayList<UserSocket> arrayList = new ArrayList<>();
    UserAdapter userAdapter;
    public static Socket socket;
    ClientThread clientThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InitializeVariables();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleEvents();
    }

    public void InitializeVariables() throws IOException {
        toolbar = findViewById(R.id.activity_main_material_toolbar);
        listView = findViewById(R.id.activity_main_list_view);
        userAdapter = new UserAdapter(this, arrayList);
        listView.setAdapter(userAdapter);
        socket = new Socket("127.0.0.1", 3000);
        UserSocket user = new UserSocket("Elad", "I like basketball");
        clientThread = new ClientThread(user, socket, getApplicationContext());
        clientThread.start();
    }

    public void HandleEvents(){
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.change_profile){
                    Intent intent = new Intent(getApplicationContext(), Profile.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserSocket chosenUser = arrayList.get(i);
                try {
                    clientThread.sendChoiceToServer(chosenUser.getNickname());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}