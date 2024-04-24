package com.example.rcl_app;

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

    public String getRequestItemName()
    {
        return requestItemName;
    }

    public int getRequestItemQuantity()
    {
        return requestItemQuantity;
    }
}
