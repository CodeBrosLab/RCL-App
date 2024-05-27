/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.services;

import com.example.backend_rcl.model.RecycleRequest;
import com.example.backend_rcl.repositories.RecycleRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecycleRequestsService
{
    @Autowired
    private RecycleRequestsRepository recycleRequestsRepository;

    public void saveRecycleRequest(RecycleRequest recycleRequest)
    {
        recycleRequestsRepository.save(recycleRequest);
    }

    public List<RecycleRequest> findAllRequests()
    {
        return recycleRequestsRepository.findAll();
    }

    public RecycleRequest findRecycleRequest(int request_id)
    {
        return recycleRequestsRepository.findById(request_id).get();
    }

    public void deleteRecycleRequest(RecycleRequest recycleRequest)
    {
        recycleRequestsRepository.delete(recycleRequest);
    }

}
