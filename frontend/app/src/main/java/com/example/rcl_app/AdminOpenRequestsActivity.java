package com.example.rcl_app;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminOpenRequestsActivity extends AppCompatActivity {

    private ExpandableListView openRequestsListView;
    private OpenRequestExpandableListViewAdapter openRequestAdapter;
    private List<OpenRequestDetails> openRequestDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_open_requests);

        //TESTING...
        openRequestsListView = findViewById(R.id.openRequestsList);

        openRequestDetailsList = new ArrayList<>();

        List<RequestListItem> requestList = new ArrayList<>();
        requestList.add(new RequestListItem(1, "Glass", 10));
        requestList.add(new RequestListItem(2, "Paper", 30));

        openRequestDetailsList.add(new OpenRequestDetails(1, "thanos", requestList));
        openRequestDetailsList.add(new OpenRequestDetails(2, "andreas", requestList));
        openRequestDetailsList.add(new OpenRequestDetails(3, "george", requestList));

        openRequestAdapter = new OpenRequestExpandableListViewAdapter(this, openRequestDetailsList);
        openRequestsListView.setAdapter(openRequestAdapter);

    }
}
