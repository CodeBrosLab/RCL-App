/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl.repositories;

import com.example.backend_rcl.model.User;
import com.example.backend_rcl.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer>
{
    /*Custom JPQL (Java Persistence Query Language) query that is similar to SQL query but works on
      entity objects rather than database tables
      So, in summary, this query retrieves data from the User entity, constructs new UserDTO objects with
      username and total_points fields, orders the results by total_points in descending order,
      and returns the result set.
     */
    @Query(value = "SELECT new com.example.backend_rcl.model.UserDTO(u.username, u.total_points) FROM User u ORDER BY u.total_points DESC LIMIT 3")
    public List<UserDTO> top3Users();
}
