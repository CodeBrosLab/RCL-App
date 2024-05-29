package com.example.rcl_app.http_requests;

import android.content.Context;
import android.util.Log;

import com.example.rcl_app.R;
import com.example.rcl_app.model.RecycleItem;
import com.example.rcl_app.model.RequestListItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UpdateRecycleItemPointsOkHttpHandler {

    private String ip;
    private Context context;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public UpdateRecycleItemPointsOkHttpHandler (Context context) {
        this.context = context;
    }


    public List<RecycleItem> getRecycleItemsWithPoints()
    {
        List<RecycleItem> recycleItemList = new ArrayList<>();
        ip = context.getString(R.string.ipv4);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("http://" + ip + ":8080/items")
                .build();

        Response response = null;
        try
        {
            response = client.newCall(request).execute();

            JSONArray responseArray = new JSONArray(response.body().string());
            for(int i = 0; i < responseArray.length(); i++)
            {
                JSONObject jsonObject = responseArray.getJSONObject(i);

                String itemName = jsonObject.getString("name");
                int points = jsonObject.getInt("points");

                RecycleItem recycleItem = new RecycleItem(itemName, points);
                recycleItemList.add(recycleItem);
            }
        }
        catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            response.close();
        }

        return recycleItemList;
    }

    public void updatePostRequest(List<RecycleItem> recycleItemList) throws Exception
    {
        String jsonBody = new GsonBuilder().create().toJson(recycleItemList);

        ip = context.getString(R.string.ipv4);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder().url("http://" + ip + ":8080/update_recycle_items_points").post(body).build();
        Response response = client.newCall(request).execute();

    }

}
