/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recycle_items")
public class RecycleItem
{
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "points")
    private int points;

    public RecycleItem(){}

    public RecycleItem(String aName, int somePoints)
    {
        this.name = aName;
        this.points = somePoints;
    }

    public boolean hasName(String aName)
    {
        return this.name.equals(aName);
    }

    public String getName()
    {
        return name;
    }

    public int getPoints()
    {
        return points;
    }
}
