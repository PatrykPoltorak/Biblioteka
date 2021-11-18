package com.example.Biblioteka.service.serviceInterface;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.Biblioteka.entity.Borrow;
import com.example.Biblioteka.entity.User;

public interface BorrowInterface {
	void giveBack(int borrowId);
	void cancel(int borrowId);
	void accept(Borrow borrow);
	void reservation(int bookId, UserDetails customUser);
}
