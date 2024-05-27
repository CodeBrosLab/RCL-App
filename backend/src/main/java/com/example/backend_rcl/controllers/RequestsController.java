/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.

Available Endpoints:

/login
/register
/user/{id}
/items
/recycle_request
/open_requests
/give_rewards
/top3Users
/update_recycle_items_points
/decline_request

How to pass data to endpoints:

/login -> Post request
Json Request Body:

{
    "username" : "your username",
    "password" : "your password"
}


/register -> Post request
Json Request Body:

{
    "username": "your username",
    "password": "your password"
}


/user/{id} -> Get request and replace the {id} with a number... for example /user/2

/items -> Get request

/recycle_request -> Post request
Json Request Body Example:

{
    "user_id" : 2,
    "requestItemsList" : [
        {
            "name": "Paper",
            "quantity": 10
        },
        {
            "name": "Glass",
            "quantity": 30
        }
    ]
}


/open_requests -> Get request

/top3Users -> Get request


/give_rewards -> Post request
Json Request Body Example:

{
    "request_id":1
}


/update_recycle_items_points -> Post request
Json Request Body Example:

[
    {
        "name": "Aluminium",
        "points": 25
    },
    {
        "name": "Glass",
        "points": 20
    },
    {
        "name": "Paper",
        "points": 50
    }
]


/decline_request -> Post request
Json Request Body Example:

{
    "request_id":1
}

 */

package com.example.backend_rcl.controllers;

import com.example.backend_rcl.model.*;
import com.example.backend_rcl.services.RecycleItemsService;
import com.example.backend_rcl.services.RecycleRequestsService;
import com.example.backend_rcl.services.RecycleRequestListItemsService;
import com.example.backend_rcl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RequestsController
{
    @Autowired
    private UserService userService;
    @Autowired
    private RecycleItemsService recycleItemsService;
    @Autowired
    private RecycleRequestsService recycleRequestsService;
    @Autowired
    private RecycleRequestListItemsService recycleRequestListItemsService;

    @PostMapping(path = "/register")
    public int createUser(@RequestBody User user)
    {
        userService.createUser(user);
        return userService.returnUser(user.getUsername()).getId(); //return the id in order to redirect the user to his main page...request to /user/{id} for that
    }

    @PostMapping(path = "/login")
    public int login(@RequestBody LoginRequest loginRequest)
    {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        return userService.login(username, password);
    }

    @GetMapping(path = "/user/{id}")
    public User returnUser(@PathVariable int id)
    {
        return userService.returnUser(id);
    }

    @GetMapping(path = "/items")
    public List<RecycleItem> getAllRecycleItems()
    {
        return recycleItemsService.getAllRecycleItems();
    }

    @PostMapping(path = "/recycle_request")
    public void recycleRequest(@RequestBody RecycleRequestDTO userRequestDTO)
    {
        //I get the RecycleRequestDTO which is a simpler representation of RecycleRequest,
        //and I am creating a new RecycleRequest object in order to save it to the db
        int user_id = userRequestDTO.getUser_id();

        //I do not pass the username to the json request for simplicity reasons.
        // Just a single user_id for the request is ok and then i will get the username from the repository
        String username = userService.returnUser(user_id).getUsername();

        RecycleRequest recycleRequest = new RecycleRequest();
        recycleRequest.setUser_id(user_id);
        recycleRequest.setUsername(username);
        recycleRequestsService.saveRecycleRequest(recycleRequest);

        List<RecycleRequestListItem> requestItemsList = userRequestDTO.getRequestItemsList().stream().map(itemDTO -> {
            RecycleRequestListItem recycleRequestListItem = new RecycleRequestListItem();
            recycleRequestListItem.setName(itemDTO.getName());
            recycleRequestListItem.setQuantity(itemDTO.getQuantity());
            recycleRequestListItem.setRecycle_request(recycleRequest);
            return recycleRequestListItem;
        }).toList();
        recycleRequest.setRequestList(requestItemsList);

        recycleRequestListItemsService.saveRequestListItemList(requestItemsList);
    }

    @GetMapping(path = "/open_requests")
    public List<RecycleRequestDTO> showOpenRequests()
    {
        List<RecycleRequestDTO> openRequestsDTO = new ArrayList<>();
        List<RecycleRequest> openRequestsFromDB = recycleRequestsService.findAllRequests();

        //I will convert the original RecycleRequest items that are on the db to a similar
        //representation with some fields less because they do not need to be shown to the admin
        for(RecycleRequest r : openRequestsFromDB)
        {
            RecycleRequestDTO rDTO = new RecycleRequestDTO();
            rDTO.setRequest_id(r.getId());
            rDTO.setUsername(r.getUsername());
            rDTO.setUser_id(r.getUser_id());
            rDTO.setRequestItemsList(r.getRequestList().stream().map(item -> {
                RecycleRequestListItemDTO temp = new RecycleRequestListItemDTO();
                temp.setName(item.getName());
                temp.setQuantity(item.getQuantity());
                return temp;
            }).toList());

            openRequestsDTO.add(rDTO);
        }

        return openRequestsDTO;
    }

    @PostMapping(path = "/update_recycle_items_points")
    public void updateRecycleItemsPoints(@RequestBody List<RecycleItem> newPointsList)
    {
        recycleItemsService.updatePoints(newPointsList);
    }

    @PostMapping(path = "/give_rewards")
    public void giveRewards(@RequestBody Map<String, Integer> requestBody)
    {
        int request_id = requestBody.get("request_id");
        RecycleRequest recycleRequest = recycleRequestsService.findRecycleRequest(request_id);

        if(recycleRequest != null)
        {
            List<RecycleRequestListItem> requestList = recycleRequest.getRequestList();
            int requestPoints = recycleItemsService.calculateTotalPointsForRequest(requestList);

            User user = userService.returnUser(recycleRequest.getUser_id());
            user.addPoints(requestPoints);
            userService.updateUser(user);

            recycleRequestListItemsService.deleteRequestList(requestList);
            recycleRequestsService.deleteRecycleRequest(recycleRequest);
        }
    }

    @PostMapping(path = "/decline_request")
    public void declineRequest(@RequestBody Map<String, Integer> requestBody)
    {
        int request_id = requestBody.get("request_id");
        RecycleRequest recycleRequest = recycleRequestsService.findRecycleRequest(request_id);

        if(recycleRequest != null)
        {
            List<RecycleRequestListItem> requestList = recycleRequest.getRequestList();
            recycleRequestListItemsService.deleteRequestList(requestList);
            recycleRequestsService.deleteRecycleRequest(recycleRequest);
        }
    }

    @GetMapping(path = "/top3Users")
    public List<UserDTO> top3Users()
    {
        return userService.top3Users();
    }
}
