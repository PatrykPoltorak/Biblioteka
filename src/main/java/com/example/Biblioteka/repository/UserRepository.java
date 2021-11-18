package com.example.Biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.Biblioteka.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUsername(String name);
	
	@Query("Select u from User u where u.name = ?1")
	User findUserByName(String name);
	
	@Query("Select u from User u where u.id = ?1")
	User findUserById(int id);
	
	@Query("Select u from User u where u.surname = ?1")
	User findUserBySurname(String surname);
}
