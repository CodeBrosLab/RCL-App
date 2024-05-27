package com.example.rcl_app.http_requests;

import android.content.Context;
import android.util.Log;

import com.example.rcl_app.R;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RecycleRequestOkHttpHandler {

    private String ip;
    private Context context;
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RecycleRequestOkHttpHandler (Context context) {
        this.context = context;
        this.client = new OkHttpClient();
    }

    public void recyclePostRequest(String json) throws Exception {

        ip = context.getString(R.string.ipv4);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder().url("http://" + ip + ":8080/recycle_request").post(body).build();
        Response response = client.newCall(request).execute();

        System.out.println(json);

    }

}
