package com.example.rcl_app.model;

import java.util.List;

public class OpenRequestDetails {
    private int request_id;
    private int user_id;
    private String username;
    private List<RequestListItem> requestItemsList;

    public OpenRequestDetails(int request_id, int user_id, String username, List<RequestListItem> items) {
        this.request_id = request_id;
        this.user_id = user_id;
        this.username = username;
        this.requestItemsList = items;
    }

    public int getRequestId() {
        return request_id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public List<RequestListItem> getRequestItemsList() {
        return requestItemsList;
    }
}

