package com.example.rcl_app.model;

import com.google.gson.annotations.SerializedName;

public class RequestListItem {

    @SerializedName("name")
    private String requestItemName;
    @SerializedName("quantity")
    private int requestItemQuantity;

    public RequestListItem(String requestItemName, int requestItemQuantity) {
        this.requestItemName = requestItemName;
        this.requestItemQuantity = requestItemQuantity;
    }

    public boolean hasName(String aName) {
        return this.requestItemName.equals(aName);
    }

    public void addQuantity(int quantity) {
        this.requestItemQuantity += quantity;
    }

    public String getRequestItemName() {
        return requestItemName;
    }

    public int getRequestItemQuantity() {
        return requestItemQuantity;
    }
}
