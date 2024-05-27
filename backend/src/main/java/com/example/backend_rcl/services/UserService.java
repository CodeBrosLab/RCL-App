/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.services;

import com.example.backend_rcl.model.User;
import com.example.backend_rcl.model.UserDTO;
import com.example.backend_rcl.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UsersRepository usersRepository;

    private List<User> getAllUsers()
    {
        return usersRepository.findAll();
    }

    public void createUser(User aUser)
    {
        usersRepository.save(aUser);
    }

    public void updateUser(User aUser)
    {
        usersRepository.save(aUser);
    }

    public User returnUser(int id)
    {
        return usersRepository.findById(id).get();
    }

    public User returnUser(String username)
    {
        List<User> users = getAllUsers();

        for(User user : users)
            if(user.hasUsername(username))
                return user;
        return null;
    }

    public int login(String username, String password)
    {
        List<User> users = getAllUsers();

        for(User user : users)
            if(user.hasUsername(username) && user.hasPassword(password))
                return user.getId();

        return -1;
    }

    public List<UserDTO> top3Users()
    {
        return usersRepository.top3Users();
    }
}
