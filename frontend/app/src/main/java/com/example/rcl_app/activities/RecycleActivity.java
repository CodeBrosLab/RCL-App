package com.example.rcl_app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;
import com.example.rcl_app.http_requests.RecycleItemsOkHttpHandler;
import com.example.rcl_app.http_requests.RecycleRequestOkHttpHandler;
import com.example.rcl_app.model.RequestListItem;
import com.example.rcl_app.adapters.RequestListItemAdapter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class RecycleActivity extends AppCompatActivity {

    private EditText amountInput;
    private ImageButton arrowUpButton, arrowDownButton, backToRewardsButton;
    private Button addToListButton, recycleButton;
    private Spinner recycleTypesSpinner;

    private List<RequestListItem> requestList;
    private List<String> recycleTypesList;
    private RequestListItemAdapter itemAdapter;
    private ListView requestItemsListView;

    private RecycleItemsOkHttpHandler recycleHttp = new RecycleItemsOkHttpHandler();

    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        amountInput = findViewById(R.id.amountInput);
        arrowUpButton = findViewById(R.id.arrowUpButton);
        arrowDownButton = findViewById(R.id.arrowDownButton);
        backToRewardsButton = findViewById(R.id.backToRewardsButton);
        addToListButton = findViewById(R.id.addToListButton);
        recycleButton = findViewById(R.id.recycleButton);
        recycleTypesSpinner = findViewById(R.id.recycleTypesSpinner);

        Intent intent = getIntent();
        Integer userid = intent.getIntExtra("userid",-1); //if is -1 then we did not pass it right

        recycleTypesList = new ArrayList<>();


        try {
            recycleTypesList = recycleHttp.getRecycleItems(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> recycleTypesAdapter = new ArrayAdapter<>(RecycleActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                recycleTypesList);

        recycleTypesSpinner.setAdapter(recycleTypesAdapter);

        requestList = new ArrayList<>();

        //I needed to create a custom adapter.
        //The RequestItem class matches exactly to the Request-Item from the database model
        itemAdapter = new RequestListItemAdapter(getApplicationContext(), requestList);

        requestItemsListView = findViewById(R.id.requestItemsListView);
        requestItemsListView.setAdapter(itemAdapter);

        //add listeners for all the buttons
        backToRewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //simply terminate this intent and go back to rewards activity
            }
        });

        arrowUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(amountInput.getText().toString());
                amount++;

                amountInput.setText(Integer.toString(amount));
            }
        });

        arrowDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(amountInput.getText().toString());

                if(amount > 0) {
                    amount--;
                    amountInput.setText(Integer.toString(amount));
                }
            }
        });

        addToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(amountInput.getText().toString());
                String recycleType = recycleTypesSpinner.getSelectedItem().toString();

                addDataToList(amount, recycleType);
            }
        });

        recycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonArray jsonArray = new JsonArray();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                for (RequestListItem item : requestList) {
                    jsonArray.add(gson.toJsonTree(item));
                }
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("user_id", userid);
                jsonObject.add("requestItemsList", jsonArray); //as the backend

                String json = gson.toJson(jsonObject);

                RecycleRequestOkHttpHandler rrOkHttp = new RecycleRequestOkHttpHandler(context);
                try {
                    rrOkHttp.recyclePostRequest(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Pending Approval", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDataToList(int amount, String recycleType)
    {
        boolean existsInTheList = false;

        for(RequestListItem item : requestList) {
            if(item.hasName(recycleType)) {
                item.addQuantity(amount);
                existsInTheList = true;
                break;
            }
        }

        if(!existsInTheList)
            requestList.add(new RequestListItem(recycleType, amount));

        itemAdapter.notifyDataSetChanged();
    }
}