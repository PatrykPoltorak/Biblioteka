package com.example.Biblioteka.service;

import com.example.Biblioteka.service.Users.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import com.example.Biblioteka.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class UsersServiceTest {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private UserServiceImpl userService;
    @Value("${sql.script.create.users}")
    private String createUser;
    @Value("${sql.script.delete.users}")
    public String sqlDeleteUser;

    @BeforeEach
    public void setUp(){
        jdbc.execute(createUser);
    }

    @Test
    public void userExist(){
        Users user = new Users();
        user.setUsername("username");
        Assertions.assertTrue(userService.userExists(user.getUsername()));

    }
    @Test
    public void userNotExist(){
        Users user = new Users();
        user.setUsername("username1");
        Assertions.assertFalse(userService.userExists(user.getUsername()));
    }

    @Test
    public void usersShouldBeAdded(){
        Assertions.assertEquals(1,userService.findAll().size());
        Users user = new Users();
        user.setName("name");
        user.setSurname("surname");
        user.setUsername("username");
        user.setPassword("password");
        userService.add(user);
        Assertions.assertEquals(2,userService.findAll().size());
    }

    @AfterEach
    public void afterEach(){
        jdbc.execute(sqlDeleteUser);
    }
 }
