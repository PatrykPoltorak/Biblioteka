package com.example.Biblioteka.service.Book;

import com.example.Biblioteka.entity.Book;

import java.util.List;

public interface BookService {
	void add(Book book);
	void delate(int bookId);
	List<Book> findAll();
	Book findBookById(int id);
	boolean exists(Book book);

}
