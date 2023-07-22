package com.example.Biblioteka.service.serviceInterface;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.Biblioteka.entity.Borrow;

public interface BorrowInterface {
	void giveBack(int borrowId);
	void cancel(int borrowId);
	void accept(Borrow borrow);
	void reservation(int bookId, UserDetails customUser);
}
