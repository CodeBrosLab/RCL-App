package com.example.rcl_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcl_app.R;
import com.example.rcl_app.model.OpenRequestDetails;
import com.example.rcl_app.model.RequestListItem;

import java.util.List;

public class OpenRequestExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<OpenRequestDetails> data;

    public OpenRequestExpandableListViewAdapter(Context context,
                                                List<OpenRequestDetails> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1; //we have only one child for every header
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).getRequestItemsList();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.single_open_request_header_layout, parent, false);
        }

        TextView headerRequestId = view.findViewById(R.id.headerRequestId);
        TextView headerUsername = view.findViewById(R.id.headerUsername);

        headerRequestId.setText(Integer.toString(data.get(i).getRequestId()));
        headerUsername.setText(data.get(i).getUsername());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.single_open_request_body_layout, parent, false);
        }

        ListView adminOpenRequestBodyList = view.findViewById(R.id.adminOpenRequestBodyList);
        Button approveBtn = view.findViewById(R.id.approveBtn);
        Button declineBtn = view.findViewById(R.id.declineBtn);

        List<RequestListItem> requestItemsList = data.get(i).getRequestItemsList();

        OpenRequestBodyListAdapter bodyListAdapter = new OpenRequestBodyListAdapter(context, requestItemsList);
        adminOpenRequestBodyList.setAdapter(bodyListAdapter);

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Needs to be implemented", Toast.LENGTH_SHORT).show();
            }
        });

        declineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
