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
import com.eladnet.ServiceHandling.UserSocket;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserSocket> {
    public UserAdapter(@NonNull Context context, @NonNull List<UserSocket> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
        }
        UserSocket user = getItem(position);
        TextView nickname = convertView.findViewById(R.id.user_item_nickname);
        TextView description = convertView.findViewById(R.id.user_item_description);
        nickname.setText(user.getNickname());
        description.setText(user.getDescription());
        return convertView;
    }
}
