package com.example.Biblioteka.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
public class BorrowServiceTest {
    @Autowired
    private BorrowService borrowService;

}
