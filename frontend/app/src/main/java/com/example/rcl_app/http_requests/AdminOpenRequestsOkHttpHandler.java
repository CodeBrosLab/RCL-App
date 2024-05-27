package com.example.rcl_app.http_requests;

import android.content.Context;
import android.util.Log;

import com.example.rcl_app.R;
import com.example.rcl_app.model.OpenRequestDetails;
import com.example.rcl_app.model.RequestListItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdminOpenRequestsOkHttpHandler {

    private static final String TAG = "AdminOpenRequestsHandler";
    private String ip;
    private Context context;
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private List<OpenRequestDetails> openRequestDetailsList;

    public AdminOpenRequestsOkHttpHandler(Context context) throws Exception {
        this.context = context;
        this.client = new OkHttpClient();

        ip = context.getString(R.string.ipv4);
        Request request = new Request.Builder()
                .url("http://" + ip + ":8080/open_requests")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                Log.d(TAG, "Response: " + jsonResponse);

                JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();

                if (jsonArray != null) {
                    openRequestDetailsList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        int requestId = getIntFromJson(jsonObject, "request_id");
                        int userId = getIntFromJson(jsonObject, "user_id");
                        String username = getStringFromJson(jsonObject, "username");

                        Log.d(TAG, "Parsed Request - requestId: " + requestId + ", userId: " + userId + ", username: " + username);

                        JsonArray itemsArray = jsonObject.getAsJsonArray("requestItemsList");
                        List<RequestListItem> requestItems = new ArrayList<>();

                        if (itemsArray != null) {
                            for (int j = 0; j < itemsArray.size(); j++) {
                                JsonObject itemObject = itemsArray.get(j).getAsJsonObject();
                                String name = getStringFromJson(itemObject, "name");
                                int quantity = getIntFromJson(itemObject, "quantity");

                                Log.d(TAG, "Parsed Item - name: " + name + ", quantity: " + quantity);

                                requestItems.add(new RequestListItem( name, quantity));
                            }
                        } else {
                            Log.w(TAG, "Items array is null for requestId: " + requestId);
                        }

                        openRequestDetailsList.add(new OpenRequestDetails(requestId, userId, username, requestItems));
                    }

                    for (OpenRequestDetails details : openRequestDetailsList) {
                        Log.i(TAG, "User: " + details.getUsername());
                        for (RequestListItem item : details.getRequestItemsList()) {
                            Log.i(TAG, "Item: " + item.getRequestItemName() + ", Quantity: " + item.getRequestItemQuantity());
                        }
                    }
                } else {
                    Log.e(TAG, "JSON array is null");
                }
            } else {
                Log.e(TAG, "Response was not successful or body is null");
            }
        } catch (IOException e) {
            Log.e(TAG, "Error in executing request", e);
        }
    }

    private int getIntFromJson(JsonObject jsonObject, String memberName) {
        JsonElement element = jsonObject.get(memberName);
        return (element != null && !element.isJsonNull()) ? element.getAsInt() : 0;
    }

    private String getStringFromJson(JsonObject jsonObject, String memberName) {
        JsonElement element = jsonObject.get(memberName);
        return (element != null && !element.isJsonNull()) ? element.getAsString() : "";
    }

    public List<OpenRequestDetails> getOpenRequestDetailsList() {
        return openRequestDetailsList;
    }
}
