/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.model;

public class UserDTO
{
    private String username;
    private int total_points;

    public UserDTO(){}

    public UserDTO(String username, int total_points)
    {
        this.username = username;
        this.total_points = total_points;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getTotal_points()
    {
        return total_points;
    }

    public void setTotal_points(int total_points)
    {
        this.total_points = total_points;
    }
}
