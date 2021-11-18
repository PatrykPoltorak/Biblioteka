package com.example.Biblioteka.entity;

import javax.persistence.*;
import java.util.List;
import java.sql.Date;
@Entity
public class Book {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String authorName;
	private String authorSurname;
	private String bookTitle;
	private Date releaseDate;
	private String status;
	@OneToMany(mappedBy = "books", cascade = CascadeType.REMOVE)
	private List<Borrow>  borrowsbook;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorSurname() {
		return authorSurname;
	}
	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Borrow> getBorrowsbook() {
		return borrowsbook;
	}
	public void setBorrowsbook(List<Borrow> borrowsbook) {
		this.borrowsbook = borrowsbook;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", authorName=" + authorName + ", authorSurname=" + authorSurname + ", bookTitle="
				+ bookTitle + ", releaseDate=" + releaseDate + ", status=" + status + "]";
	}
	

}
