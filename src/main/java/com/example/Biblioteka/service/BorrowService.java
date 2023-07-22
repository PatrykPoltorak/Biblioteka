package com.example.Biblioteka.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Biblioteka.entity.Borrow;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.repository.BorrowRepository;
import com.example.Biblioteka.repository.UserRepository;
import com.example.Biblioteka.service.serviceInterface.BorrowInterface;
@Service
public class BorrowService implements BorrowInterface{
	private BorrowRepository  borrowRepository;
	private BookRepository bookRepository;
	private UserRepository userRepository;
	
	public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository,
			UserRepository userRepository) {
		super();
		this.borrowRepository = borrowRepository;
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void reservation(int bookId, UserDetails customUser) {
		Borrow borrow = new Borrow();
		borrow.setUsers(userRepository.findUserByUsername(customUser.getUsername()));
		borrow.setBooks(bookRepository.findBookById(bookId));
		borrow.setDateBorrow(null);
		borrow.setDateGiveBack(null);
		borrow.setStatus("Zarezerwowana");
		borrowRepository.save(borrow);
	}

	@Override
	public void accept(Borrow borrow) {			
		borrow.setDateBorrow(new Date());
		borrow.setStatus("Wydana");
		borrowRepository.save(borrow);
	}
	
	@Override
	public void giveBack(int borrowId) {
		Borrow borrow = borrowRepository.findBorrowById(borrowId);
		borrow.setDateGiveBack(new Date());
		borrow.setStatus("Zwrócona");
		borrowRepository.save(borrow);
	}

	@Override
	public void cancel(int borrowId) {
		Borrow borrow = borrowRepository.findBorrowById(borrowId);
		borrow.setDateBorrow(new Date());
		borrow.setDateGiveBack(new Date());
		borrow.setStatus("Rezygnacja");
		borrowRepository.save(borrow);
	}


}
