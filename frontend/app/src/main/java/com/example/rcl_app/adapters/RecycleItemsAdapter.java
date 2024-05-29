package com.example.rcl_app.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.rcl_app.R;
import com.example.rcl_app.model.RecycleItem;
import com.example.rcl_app.model.RequestListItem;

import java.util.List;

public class RecycleItemsAdapter extends ArrayAdapter<RecycleItem>
{
    private List<RecycleItem> recycleItemList;
    public RecycleItemsAdapter(@NonNull Context context, List<RecycleItem> items)
    {
        super(context, 0, items);
        this.recycleItemList = items;
    }

    public List<RecycleItem> getRecycleItemList()
    {
        return this.recycleItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RecycleItem item = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.update_recycle_items_list_layout, parent, false);


        TextView itemName = convertView.findViewById(R.id.itemName);
        EditText editItemInput = convertView.findViewById(R.id.editItemInput);

        itemName.setText(item.getName());
        editItemInput.setText(Integer.toString(item.getPoints()));

        editItemInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int points = Integer.parseInt(s.toString());
                    recycleItemList.get(position).setPoints(points);
                    notifyDataSetChanged();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }
}
