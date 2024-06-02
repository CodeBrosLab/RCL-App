/*
Author: Thanos Moschou
Description: This is a rest api used for mobile assignment of UoM in the 2023-2024 spring semester.
*/

package com.example.backend_rcl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseInitializer
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @PostConstruct //this method will be called after dependency injection in order to initialize the db
    public void initialize()
    {
        //If tables already exist, but they are empty, the code inside the if statement will be
        //executed, filling the tables with demo data. If tables already exist,
        //but they are not empty, nothing
        //will be executed, leaving the existing data inside the database.
        //If tables do not exist, the catch part will be executed, creating the tables
        //and filling them with demo data.

        try
        {
            Integer userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users;", Integer.class);
            if(userCount == 0)
            {
                ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(
                        new ClassPathResource("schema.sql"),
                        new ClassPathResource("data.sql")
                );

                resourceDatabasePopulator.execute(dataSource);
            }
        }
        catch(Exception e)
        {
            // Handle exception if the table does not exist yet
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(
                    new ClassPathResource("schema.sql"),
                    new ClassPathResource("data.sql")
            );

            resourceDatabasePopulator.execute(dataSource);
        }
    }
}
