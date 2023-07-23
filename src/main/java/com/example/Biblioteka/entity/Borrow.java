package com.example.Biblioteka.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="books")
	private Book books;
	@ManyToOne
	private Users users;
	private String status;
	private Date dateBorrow;
	private Date dateGiveBack;

}
