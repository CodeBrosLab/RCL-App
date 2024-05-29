package com.example.rcl_app.model;

public class RecycleItem
{
    private String name;
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

    public void setPoints(int points)
    {
        if(points > 0)
            this.points = points;
    }

    public void printInfo()
    {
        System.out.println(name + " " + points);
    }
}
