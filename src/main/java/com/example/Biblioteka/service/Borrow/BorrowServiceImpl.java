package com.example.Biblioteka.service.Borrow;

import java.util.Date;
import java.util.List;

import com.example.Biblioteka.entity.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Biblioteka.entity.Borrow;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.repository.BorrowRepository;
import com.example.Biblioteka.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BorrowServiceImpl implements BorrowService {
	private BorrowRepository  borrowRepository;
	private BookRepository bookRepository;
	private UserRepository userRepository;

	public Users findUserBySurname(String surname){
		return userRepository.findUserBySurname(surname);
	}
	public List<Borrow> findBorrowForUser(int id){
		return borrowRepository.findBorrowForUser(id);
	}
	public List<Borrow> findAll(){
		return borrowRepository.findAll();
	}
	public List<Borrow> findBorrowByReleaseStatus(){
		return borrowRepository.findBorrowByStatus("Zarezerwowana");
	}
	public List<Borrow> findBorrowByGiveBackStatus(){
		return borrowRepository.findBorrowByStatus("Wydana");
	}

	public Borrow findById(int borrowId){
		return borrowRepository.findBorrowById(borrowId);
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
	@Transactional
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
