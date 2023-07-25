package com.example.Biblioteka.service.Borrow;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.Biblioteka.entity.Borrow;

import java.util.List;

public interface BorrowService {
	void giveBack(int borrowId);
	void cancel(int borrowId);
	void releaseBook(int borrowId);
	void reservation(int bookId, UserDetails customUser);
	List<Borrow> findBorrowForUser(int id);
	List<Borrow> findAll();
	List<Borrow> findBorrowByReleaseStatus();
	List<Borrow> findBorrowByGiveBackStatus();
	Borrow findById(int borrowId);
	List<Borrow> findBorrowByStatus(String status);


}
