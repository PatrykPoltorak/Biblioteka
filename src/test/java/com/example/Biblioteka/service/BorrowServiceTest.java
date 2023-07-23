package com.example.Biblioteka.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class BorrowServiceTest {
    @Autowired
    private BorrowService borrowService;

    @BeforeEach
    public void setup(){

    }

    @Test
    public void newUserAreCreated(){

    }

}
