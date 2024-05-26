package com.example.rcl_app.http_requests;

import android.content.Context;
import android.widget.TextView;

import com.example.rcl_app.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Top3UsersOkHttpHandler {

    private Context context;
    private String ip;


    public Top3UsersOkHttpHandler(Context context, TextView top1,TextView top2,TextView top3) throws Exception {

        this.context = context;
        ip = context.getString(R.string.ipv4);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+ ip +":8080/top3Users").build();
        Response response = client.newCall(request).execute();

        JSONArray jsonArray = new JSONArray(response.body().string());

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String username = jsonObject.getString("username");
            String points = jsonObject.getString("total_points");

            if(i==0)
                top1.setText(username + " " +points);
            else if(i==1)
                top2.setText(username + " " +points);
            if(i==2)
                top3.setText(username + " " +points);

        }


    }
}
