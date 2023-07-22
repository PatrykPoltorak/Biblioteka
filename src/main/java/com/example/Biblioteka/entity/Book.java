package com.example.Biblioteka.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.sql.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String authorName;
	@NotNull(message = "pole wymagane")
	private String authorSurname;
	@NotNull(message = "pole wymagane")
	private String bookTitle;
	@NotNull(message = "pole wymagane")
	private Date releaseDate;
	@OneToMany(mappedBy = "books", cascade = CascadeType.REMOVE)
	private List<Borrow>  borrowsbook;

}
