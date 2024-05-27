/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.services;

import com.example.backend_rcl.model.RecycleItem;
import com.example.backend_rcl.model.RecycleRequestListItem;
import com.example.backend_rcl.repositories.RecycleItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecycleItemsService
{
    @Autowired
    private RecycleItemsRepository recycleItemsRepository;

    public List<RecycleItem> getAllRecycleItems()
    {
        return recycleItemsRepository.findAll();
    }

    public RecycleItem getRecycleItemByName(String aName)
    {
        List<RecycleItem> items = getAllRecycleItems();

        for(RecycleItem item : items)
        {
            if(item.hasName(aName))
                return item;
        }

        return null;
    }

    public void updatePoints(List<RecycleItem> items)
    {
        recycleItemsRepository.saveAll(items);
    }

    public int calculateTotalPointsForRequest(List<RecycleRequestListItem> items)
    {
        int points = 0;

        for(RecycleRequestListItem recycleRequestListItem : items)
        {
            String requestItemName = recycleRequestListItem.getName();
            int requestItemQuantity = recycleRequestListItem.getQuantity();

            RecycleItem recycleItem = getRecycleItemByName(requestItemName);

            if(recycleItem != null)
                points += (requestItemQuantity * recycleItem.getPoints());
        }

        return points;
    }
}
