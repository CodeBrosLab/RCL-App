/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "total_points")
    private int total_points;

    public User(){}

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.total_points = 0;
    }

    public int getId()
    {
        return id;
    }

    public int getTotal_points()
    {
        return total_points;
    }

    public String getUsername()
    {
        return username;
    }

    public void setPassword(String aPassword)
    {
        this.password = aPassword;
    }

    public void setUsername(String aUsername)
    {
        this.username = aUsername;
    }

    public void setTotal_points(int total_points)
    {
        this.total_points = total_points;
    }

    public boolean hasUsername(String aUsername)
    {
        return this.username.equals(aUsername);
    }

    public boolean hasPassword(String aPassword)
    {
        return this.password.equals(aPassword);
    }

    public void addPoints(int somePoints)
    {
        if(somePoints >= 0)
            this.total_points += somePoints;
    }
}
