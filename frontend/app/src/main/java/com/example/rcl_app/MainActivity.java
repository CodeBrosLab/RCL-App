package com.example.rcl_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        //TESTING THE ITEMS LAYOUT...
        List<RequestListItem> requestList = new ArrayList<>();
        requestList.add(new RequestListItem(1, "Glass", 10));
        requestList.add(new RequestListItem(2, "Paper", 30));

        //I needed to create a custom adapter.
        //The RequestItem class matches exactly to the Request-Item from the database model
        RequestListItemAdapter itemAdapter = new RequestListItemAdapter(getApplicationContext(), requestList);

        ListView requestItemsListView = findViewById(R.id.requestItemsListView);
        requestItemsListView.setAdapter(itemAdapter);
    }
}