package com.example.rcl_app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;
import com.example.rcl_app.http_requests.UserPointsOkHttpHandler;

public class YourRewardsActivity extends AppCompatActivity {

    private Button rclButton;
    private UserPointsOkHttpHandler getUserPoints;
    private int userPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rewards);

        getUserPoints = new UserPointsOkHttpHandler(this);

        Intent intent = getIntent();
        Integer userid = intent.getIntExtra("userid",-1); //if is -1 then we did not pass it right

        try {
            userPoints = getUserPoints.getUserPoints(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView totalPoints = findViewById(R.id.totalPoints);
        totalPoints.setText("Your Total Points Are: " + userPoints);

        rclButton = findViewById(R.id.rclButton);
        rclButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recycleIntent = new Intent(YourRewardsActivity.this, RecycleActivity.class);
                recycleIntent.putExtra("userid",userid);
                startActivity(recycleIntent);
            }
        });
    }
}
