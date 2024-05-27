/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.model;

import java.util.List;

public class RecycleRequestDTO
{
    //Keep in mind that when you send requests, you keep the name of the keys similar to class attributes
    private int request_id;
    private int user_id;
    private String username;
    private List<RecycleRequestListItemDTO> requestItemsList;

    public RecycleRequestDTO(){}

    public RecycleRequestDTO(int user_id, List<RecycleRequestListItemDTO> requestItemsList)
    {
        this.user_id = user_id;
        this.requestItemsList = requestItemsList;
    }

    public RecycleRequestDTO(int request_id, String username, int user_id, List<RecycleRequestListItemDTO> requestItemsList)
    {
        this.request_id = request_id;
        this.user_id = user_id;
        this.username = username;
        this.requestItemsList = requestItemsList;
    }

    public int getRequest_id()
    {
        return request_id;
    }

    public void setRequest_id(int request_id)
    {
        this.request_id = request_id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public List<RecycleRequestListItemDTO> getRequestItemsList()
    {
        return requestItemsList;
    }

    public void setRequestItemsList(List<RecycleRequestListItemDTO> requestItemsList)
    {
        this.requestItemsList = requestItemsList;
    }
}
