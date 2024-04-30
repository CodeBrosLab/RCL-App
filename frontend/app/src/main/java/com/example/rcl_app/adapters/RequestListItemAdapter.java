package com.example.rcl_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.rcl_app.R;
import com.example.rcl_app.model.RequestListItem;

import java.util.List;

public class RequestListItemAdapter extends ArrayAdapter<RequestListItem>
{

    public RequestListItemAdapter(@NonNull Context context, List<RequestListItem> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RequestListItem item = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.request_list_single_item_layout, parent, false);


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
