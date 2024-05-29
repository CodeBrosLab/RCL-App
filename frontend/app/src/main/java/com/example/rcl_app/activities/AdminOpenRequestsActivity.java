package com.example.rcl_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;
import com.example.rcl_app.adapters.OpenRequestExpandableListViewAdapter;
import com.example.rcl_app.http_requests.AdminOpenRequestsOkHttpHandler;
import com.example.rcl_app.model.OpenRequestDetails;

import java.util.ArrayList;
import java.util.List;

public class AdminOpenRequestsActivity extends AppCompatActivity {

    private ExpandableListView openRequestsListView;
    private OpenRequestExpandableListViewAdapter openRequestAdapter;
    private List<OpenRequestDetails> openRequestDetailsList;
    private ImageButton backToLogin;
    private TextView editItemPointsBtn;
    private Button statsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_open_requests);

        backToLogin = findViewById(R.id.backToLogin);
        statsBtn = findViewById(R.id.statsBtn);
        editItemPointsBtn = findViewById(R.id.editItemPointsBtn);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statsIntent = new Intent(AdminOpenRequestsActivity.this, StatisticsActivity.class);
                startActivity(statsIntent);
            }
        });

        editItemPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updatePointsIntent = new Intent(AdminOpenRequestsActivity.this, UpdateRecycleItemPointsActivity.class);
                startActivity(updatePointsIntent);
            }
        });

        openRequestsListView = findViewById(R.id.openRequestsList);

        openRequestDetailsList = new ArrayList<>();

        try {
            AdminOpenRequestsOkHttpHandler aorOkHttpHandler = new AdminOpenRequestsOkHttpHandler(this);
            openRequestDetailsList = aorOkHttpHandler.getOpenRequestDetailsList();

            if (openRequestDetailsList != null) {
                openRequestAdapter = new OpenRequestExpandableListViewAdapter(this, openRequestDetailsList);
                openRequestsListView.setAdapter(openRequestAdapter);
            } else {
                Toast.makeText(this, "Failed to load requests", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading requests", Toast.LENGTH_SHORT).show();
        }

    }

    public void logout(View view){
        Intent logout = new Intent(AdminOpenRequestsActivity.this, LoginActivity.class);
        startActivity(logout);

    }
}