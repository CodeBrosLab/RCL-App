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


public class StatisticsActivity extends AppCompatActivity {

    private Top3UsersOkHttpHandler top3Users;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        TextView top1, top2, top3;
        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);

        try {
            top3Users = new Top3UsersOkHttpHandler(context, top1, top2, top3);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void backToAdminActivity(View view){
        Intent adminOpenRequestsIntent = new Intent(StatisticsActivity.this, AdminOpenRequestsActivity.class);
        startActivity(adminOpenRequestsIntent);
    }


}

