package com.example.Biblioteka.service.serviceInterface;

import java.util.Date;

import com.example.Biblioteka.entity.Book;

public interface BookInterface {
	void add(Book book);
	void delate(int bookId);
}
