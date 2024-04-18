package com.example.rcl_app;

public class RequestItem
{
    private int requestItemId;
    private String requestItemName;
    private int requestItemQuantity;

    public RequestItem(int requestItemId, String requestItemName, int requestItemQuantity)
    {
        this.requestItemId = requestItemId;
        this.requestItemName = requestItemName;
        this.requestItemQuantity = requestItemQuantity;
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
