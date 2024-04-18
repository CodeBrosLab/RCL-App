package com.example.rcl_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class RequestListItemAdapter extends ArrayAdapter<RequestItem>
{

    public RequestListItemAdapter(@NonNull Context context, List<RequestItem> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RequestItem item = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.request_rewards_list_single_item_layout, parent, false);


        TextView requestItemQuantityTxt = convertView.findViewById(R.id.requestItemQuantityTxt);
        TextView requestItemName = convertView.findViewById(R.id.requestItemName);
        ImageView deleteRequestItemBtn = convertView.findViewById(R.id.deleteRequestItemBtn);

        requestItemQuantityTxt.setText(Integer.toString(item.getRequestItemQuantity()));
        requestItemName.setText(item.getRequestItemName());

        // Set OnClickListener for the delete ImageView
        deleteRequestItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete action
                // For example, you can remove the item from the list and notify the adapter
                remove(item);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}
