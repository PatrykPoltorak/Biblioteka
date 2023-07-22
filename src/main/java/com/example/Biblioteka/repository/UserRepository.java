package com.example.Biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.Biblioteka.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findUserByUsername(String name);
	
	@Query("Select u from User u where u.name = ?1")
    Users findUserByName(String name);
	
	@Query("Select u from User u where u.id = ?1")
    Users findUserById(int id);
	
	@Query("Select u from User u where u.surname = ?1")
    Users findUserBySurname(String surname);
}
