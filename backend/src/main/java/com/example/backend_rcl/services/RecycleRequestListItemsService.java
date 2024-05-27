/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.services;

import com.example.backend_rcl.model.RecycleRequestListItem;
import com.example.backend_rcl.repositories.RecycleRequestListItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecycleRequestListItemsService
{
    @Autowired
    private RecycleRequestListItemsRepository recycleRequestListItemsRepository;

    public void saveRequestListItemList(List<RecycleRequestListItem> list)
    {
        recycleRequestListItemsRepository.saveAll(list);
    }

    public void deleteRequestList(List<RecycleRequestListItem> items)
    {
        recycleRequestListItemsRepository.deleteAll(items);
    }
}
