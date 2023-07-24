package com.example.Biblioteka.service.Book;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.Biblioteka.entity.Book;
import com.example.Biblioteka.repository.BookRepository;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	private BookRepository bookRepository;

	public List<Book> findAll(){
		return bookRepository.findAll();
	}

	public Book findBookById(int id){
		return bookRepository.findBookById(id);
	}
	@Override

	public void add(Book book) {
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
		bookRepository.deleteById(bookId);
	}

}
