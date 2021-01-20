package com.eladnet.ServiceHandling.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eladnet.R;
import com.eladnet.ServiceHandling.Message;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, @NonNull List<Message> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_item, parent, false);

        Message message = getItem(position);
        TextView nickname = convertView.findViewById(R.id.message_item_nickname);
        TextView body = convertView.findViewById(R.id.message_item_body);
        nickname.setText(message.getNickname());
        body.setText(message.getText());
        return convertView;
    }
}
