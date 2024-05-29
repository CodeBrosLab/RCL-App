package com.example.rcl_app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;
import com.example.rcl_app.http_requests.UserPointsOkHttpHandler;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class YourRewardsActivity extends AppCompatActivity {

    private Button rclButton;
    private UserPointsOkHttpHandler getUserPoints;
    private int userPoints;
    private TextView totalPointsTextView;
    private CircularProgressIndicator progressBar;
    private ImageView logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rewards);

        logoutButton = findViewById(R.id.logout_button);
        totalPointsTextView = findViewById(R.id.totalPointsTextView);
        progressBar = findViewById(R.id.progressBar);

        getUserPoints = new UserPointsOkHttpHandler(this);

        Intent intent = getIntent();
        Integer userid = intent.getIntExtra("userid",-1); //if is -1 then we did not pass it right

        try {
            userPoints = getUserPoints.getUserPoints(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        totalPointsTextView.setText(Integer.toString(userPoints));

        //We give rewards every 1000 points. To make user see his progress we keep the modulo of the division of his points with the max value that
        //can be represented from our progressbar.
        //If he has 1800 points the next reward is in 2000 points so 1800 % 1000 = 800 and this is the value that will be shown to the progress bar
        progressBar.setProgress(userPoints % 1000, true);

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

    public void logout(View view){
        Intent logout = new Intent(YourRewardsActivity.this, LoginActivity.class);
        startActivity(logout);

    }
}
