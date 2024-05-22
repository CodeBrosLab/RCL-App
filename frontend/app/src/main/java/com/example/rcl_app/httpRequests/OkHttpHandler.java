package com.example.rcl_app.httpRequests;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {

    public OkHttpHandler() {

        //Strict mode for simplicity
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String makeHttpRequest(String url, String method, String jsonInputString) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = null;

        if (jsonInputString != null) {
            body = RequestBody.create(jsonInputString, MediaType.parse("text/plain"));
        }

        Request.Builder builder = new Request.Builder().url(url);

        switch (method.toUpperCase()) {
            case "GET":
                builder.get();
                break;
            case "POST":
                builder.post(body);
                break;
            case "PUT":
                builder.put(body);
                break;
            case "DELETE":
                if (body != null) {
                    builder.delete(body);
                } else {
                    builder.delete();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown method: " + method);
        }

        Request request = builder.build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.body().string();
    }
}
