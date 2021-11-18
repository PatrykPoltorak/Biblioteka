package com.example.Biblioteka.repository;
import java.util.List;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Biblioteka.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	@Query("Select b from Book b where b.id = ?1")
	Book findBookById(int id); 
	
	@Query("Select b from Book b where b.authorSurname = ?1")
	List<Book> findBookByAuthor(String name);
	
	@Query("Select b from Book b where b.bookTitle = ?1")
	List<Book> findBookByTitle(String title);
	
	@Query("Select b from Book b where b.releaseDate = ?1")
	List<Book> findBookByReleaseDate(Date releaseDate);
}
