package com.example.rcl_app.http_requests;

import android.content.Context;

import com.example.rcl_app.R;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserPointsOkHttpHandler {

    private int totalUserPoints;
    private Context context;
    private String ip;

    public UserPointsOkHttpHandler(Context context){
        this.context = context;
        ip = context.getString(R.string.ipv4);
    }

    public int getUserPoints(int userid) throws Exception {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+ ip +":8080/user/" + userid).build();
        Response response = client.newCall(request).execute();
        try {
            String responseBody = response.body().string();

            JSONObject jsonObject = new JSONObject(responseBody);

            totalUserPoints = jsonObject.getInt("total_points");

        }catch (Exception e){
            e.printStackTrace();
        }

        return totalUserPoints;
    }
}
