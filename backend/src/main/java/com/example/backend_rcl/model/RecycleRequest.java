/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recycle_requests")
public class RecycleRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int user_id;
    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "recycle_request") //this is the name of the object that is inside the RequestListItem class and is RecycleRequest type
    private List<RecycleRequestListItem> requestList;

    public RecycleRequest(){}

    public RecycleRequest(int user_id, String username, List<RecycleRequestListItem> requestList)
    {
        this.user_id = user_id;
        this.username = username;
        this.requestList = requestList;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public List<RecycleRequestListItem> getRequestList()
    {
        return requestList;
    }

    public void setRequestList(List<RecycleRequestListItem> requestList)
    {
        this.requestList = requestList;
    }
}
