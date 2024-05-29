package com.example.rcl_app.http_requests;

import android.content.Context;

import com.example.rcl_app.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DecisionRequestOkHttpHandler {

    private Context context;
    private String ip;
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public DecisionRequestOkHttpHandler(Context context, int requestId, Boolean action) throws Exception {

        this.context = context;
        ip = context.getString(R.string.ipv4);
        String json = "{\"request_id\":" + requestId + "}";

        client = new OkHttpClient();

        RequestBody body = RequestBody.create(json, JSON);
        Request request;

        if(action){
            request = new Request.Builder().url("http://"+ ip +":8080/give_rewards").post(body).build();
        }
        else {
            request = new Request.Builder().url("http://"+ ip +":8080/decline_request").post(body).build();
        }

        Response response = client.newCall(request).execute();


    }

}
