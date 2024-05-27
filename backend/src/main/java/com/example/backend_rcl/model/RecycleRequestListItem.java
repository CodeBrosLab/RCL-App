/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "request_list_items")
public class RecycleRequestListItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private int quantity;


    @OneToOne
    @JoinColumn(name = "recycle_request_id")
    private RecycleRequest recycle_request; //keep in mind that this is an integer inside the db but JPA wants the whole RecycleRequest object in order to save its id to the db

    public RecycleRequestListItem() {}

    public RecycleRequestListItem(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public RecycleRequest getRecycle_request()
    {
        return recycle_request;
    }

    public void setRecycle_request(RecycleRequest recycle_request)
    {
        this.recycle_request = recycle_request;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
