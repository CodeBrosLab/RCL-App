package com.example.rcl_app.model;

public class RequestListItem
{
    private int requestItemId;
    private String requestItemName;
    private int requestItemQuantity;

    public RequestListItem(int requestItemId, String requestItemName, int requestItemQuantity)
    {
        this.requestItemId = requestItemId;
        this.requestItemName = requestItemName;
        this.requestItemQuantity = requestItemQuantity;
    }

    public boolean hasName(String aName) {
        return this.requestItemName.equals(aName);
    }

    public void addQuantity(int quantity) {
        this.requestItemQuantity += quantity;
    }

    public String getRequestItemName()
    {
        return requestItemName;
    }

    public int getRequestItemQuantity()
    {
        return requestItemQuantity;
    }
}
