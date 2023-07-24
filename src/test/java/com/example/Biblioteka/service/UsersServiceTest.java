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
    private void userExist(){
        Users user = new Users();
        user.setUsername("username");
        Assertions.assertTrue(userService.userExists(user.getUsername()));

    }
    @Test
    private void userNotExist(){
        Users user = new Users();
        user.setUsername("username1");
        Assertions.assertTrue(userService.userExists(user.getUsername()));

    }

    @AfterEach
    public void afterEach(){
        jdbc.execute(sqlDeleteUser);
    }
 }
