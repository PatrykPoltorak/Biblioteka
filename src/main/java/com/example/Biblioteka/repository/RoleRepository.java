package com.example.Biblioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Biblioteka.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	@Query("Select r from Role r where r.id = ?1")
	Role findRoleById(int id);
	@Query("Select r from Role r where r.name = ?1")
	Role findRoleByname(String name);
	
}
