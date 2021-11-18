package com.example.Biblioteka.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.service.serviceInterface.BookInterface;

@Service
public class BookService implements BookInterface{
	private BookRepository bookRepository;
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	@Override
	public void add(Book book) {	
		book.setStatus("Dostępna");
		bookRepository.save(book);
	}
	public boolean exists(Book book){
		List<Book> book2 = bookRepository.findBookByTitle(book.getBookTitle());
		if(!book2.isEmpty()){
			for (Book book3 : book2) {
				if(book.getAuthorSurname().equals(book3.getAuthorSurname())) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public void delate(int bookId) {
		bookRepository.delete(bookRepository.findBookById(bookId));
	}

}
