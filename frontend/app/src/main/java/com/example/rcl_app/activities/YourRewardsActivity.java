package com.example.rcl_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;

import java.util.ArrayList;
import java.util.List;

public class YourRewardsActivity extends AppCompatActivity {

    private Button rclButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rewards);

        rclButton = findViewById(R.id.rclButton);
        rclButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recycleIntent = new Intent(YourRewardsActivity.this, RecycleActivity.class);
                startActivity(recycleIntent);
            }
        });
    }
}
