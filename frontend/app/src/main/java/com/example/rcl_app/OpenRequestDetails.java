package com.example.rcl_app;

import java.util.List;

public class OpenRequestDetails {
    private int requestId;
    private String username;
    private List<RequestListItem> requestItemsList;

    public OpenRequestDetails(int requestId, String username, List<RequestListItem> items) {
        this.requestId = requestId;
        this.username = username;
        this.requestItemsList = items;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getUsername() {
        return username;
    }

    public List<RequestListItem> getRequestItemsList()
    {
        return requestItemsList;
    }
}
