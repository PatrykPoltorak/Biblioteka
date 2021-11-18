package com.example.Biblioteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Biblioteka.entity.Borrow;
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
	@Query("Select b from Borrow b where b.id = ?1")
	Borrow findBorrowById(int id);
		
	@Query("Select b from Borrow b where b.dateBorrow = ?1")
	List<Borrow> findBorrowByDataBorrow(Date dataBorrow);
	
	
	@Query("Select b from Borrow b where b.status = ?1")
	List<Borrow> findBorrowByStatus(String status);
	
	@Query("Select b from Borrow b where b.users.id = ?1")
	List<Borrow> findBorrowForUser(int id);
	
	@Query("Select b from Borrow b where b.users.id = ?1 and b.status = ?2")
	List<Borrow> findBorrowForUserStatus(int id, String status);
	
}
