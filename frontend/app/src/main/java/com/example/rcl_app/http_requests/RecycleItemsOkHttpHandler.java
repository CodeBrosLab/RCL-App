package com.example.rcl_app.http_requests;
import com.example.rcl_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;

public class RecycleItemsOkHttpHandler {

    private Context context;
    private String ip;

    public ArrayList<String> getRecycleItems(Context context) throws IOException, JSONException {

        ip = context.getString(R.string.ipv4);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://"+ ip +":8080/items").build();

        Response response = client.newCall(request).execute();

        ArrayList<String> recycleItems = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response.body().string());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                recycleItems.add(name);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw e;
        } finally {
            response.close();
        }

        return recycleItems;
    }
}

