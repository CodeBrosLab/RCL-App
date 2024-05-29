package com.example.rcl_app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;
import com.example.rcl_app.http_requests.Top3UsersOkHttpHandler;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class StatisticsActivity extends AppCompatActivity {

    private Top3UsersOkHttpHandler top3Users;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        TextView firstUsername, secondUsername, thirdUsername, firstUserPoints, secondUserPoints, thirdUserPoints;

        firstUsername = findViewById(R.id.firstUsername);
        secondUsername = findViewById(R.id.secondUsername);
        thirdUsername = findViewById(R.id.thirdUsername);
        firstUserPoints = findViewById(R.id.firstUserPoints);
        secondUserPoints = findViewById(R.id.secondUserPoints);
        thirdUserPoints = findViewById(R.id.thirdUserPoints);

        List<TextView> top3UserFieldsList = new ArrayList<>();
        top3UserFieldsList.add(firstUsername);
        top3UserFieldsList.add(firstUserPoints);
        top3UserFieldsList.add(secondUsername);
        top3UserFieldsList.add(secondUserPoints);
        top3UserFieldsList.add(thirdUsername);
        top3UserFieldsList.add(thirdUserPoints);

        try {
            top3Users = new Top3UsersOkHttpHandler(context, top3UserFieldsList);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void backToAdminActivity(View view){
        finish();
    }


}

