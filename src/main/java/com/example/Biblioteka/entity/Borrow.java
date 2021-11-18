package com.example.Biblioteka.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Book books;
	@ManyToOne
	private User users;
	private String status;
	private Date dateBorrow;
	private Date dateGiveBack;
	public Borrow() {
		super();
	}
	public Borrow(int id, Book books, User users, String status, Date dateBorrow, Date dateGiveBack) {
		super();
		this.id = id;
		this.books = books;
		this.users = users;
		this.status = status;
		this.dateBorrow = dateBorrow;
		this.dateGiveBack = dateGiveBack;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book getBooks() {
		return books;
	}
	public void setBooks(Book books) {
		this.books = books;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDateBorrow() {
		return dateBorrow;
	}
	public void setDateBorrow(Date dateBorrow) {
		this.dateBorrow = dateBorrow;
	}
	public Date getDateGiveBack() {
		return dateGiveBack;
	}
	public void setDateGiveBack(Date dateGiveBack) {
		this.dateGiveBack = dateGiveBack;
	}
	@Override
	public String toString() {
		return "Borrow [id=" + id + ", status=" + status + ", dateBorrow=" + dateBorrow + ", dateGiveBack="
				+ dateGiveBack + "]";
	}
	
	
}
