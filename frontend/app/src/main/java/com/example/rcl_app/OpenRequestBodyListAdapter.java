package com.example.rcl_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class OpenRequestBodyListAdapter extends ArrayAdapter<RequestListItem> {

    public OpenRequestBodyListAdapter(@NonNull Context context, List<RequestListItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RequestListItem item = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.open_request_body_list_single_item_layout, parent, false);


        TextView requestItemName = convertView.findViewById(R.id.openRequestItemName);
        TextView requestItemQuantityTxt = convertView.findViewById(R.id.openRequestItemQuantityTxt);

        requestItemQuantityTxt.setText(Integer.toString(item.getRequestItemQuantity()));
        requestItemName.setText(item.getRequestItemName());

        return convertView;
    }
}
