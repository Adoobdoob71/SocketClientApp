package com.eladnet.ServiceHandling.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread {

    public static Socket socket;
    public static BufferedReader input;
    public static PrintWriter output;
    public ChatThread(Socket socket){
        ChatThread.socket = socket;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleChatInput() throws IOException {
        String messageFromGuest = input.readLine();
        addToArrayList(messageFromGuest, false);
        input.close();
    }

    public static void addToArrayList(String message, boolean guest){

    }

    public static void sendMessageToServer(String message) throws Exception{
        output.println(message.trim());
        addToArrayList(message, true);
        output.close();
    }

    @Override
    public void run(){
        try {
            handleChatInput();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        run();
    }
}
