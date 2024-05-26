package com.example.rcl_app.http_requests;

import android.os.StrictMode;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginORegisterOkHttpHandler {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;

    public LoginORegisterOkHttpHandler() {
        // Strict mode for simplicity (generally not recommended for production)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.client = new OkHttpClient();
    }

    public int loginPostRequest(String url, String username, String password) throws Exception {

        String json = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();

       return Integer.parseInt(response.body().string());

    }
}
