package com.example.rcl_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rcl_app.R;
import com.example.rcl_app.adapters.RecycleItemsAdapter;
import com.example.rcl_app.http_requests.RecycleItemsOkHttpHandler;
import com.example.rcl_app.http_requests.UpdateRecycleItemPointsOkHttpHandler;
import com.example.rcl_app.model.RecycleItem;

import java.util.List;

public class UpdateRecycleItemPointsActivity extends AppCompatActivity {

    private ImageButton backToStatisticsBtn;
    private Button updatePointsBtn;
    private ListView recyclePointsEditList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recycle_item_points);

        backToStatisticsBtn = findViewById(R.id.backToStatisticsBtn);
        updatePointsBtn = findViewById(R.id.updatePointsBtn);
        recyclePointsEditList = findViewById(R.id.recyclePointsEditList);

        UpdateRecycleItemPointsOkHttpHandler updateRecycleItemPointsOkHttpHandler = new UpdateRecycleItemPointsOkHttpHandler(getApplicationContext());

        List<RecycleItem> recycleItemList = updateRecycleItemPointsOkHttpHandler.getRecycleItemsWithPoints();

        RecycleItemsAdapter recycleItemsAdapter = new RecycleItemsAdapter(getApplicationContext(), recycleItemList);
        recyclePointsEditList.setAdapter(recycleItemsAdapter);

        backToStatisticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        updatePointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    updateRecycleItemPointsOkHttpHandler.updatePostRequest(recycleItemsAdapter.getRecycleItemList());
                    Toast.makeText(getApplicationContext(), "Points updated!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}