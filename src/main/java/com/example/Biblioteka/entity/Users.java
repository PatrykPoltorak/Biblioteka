package com.example.Biblioteka.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 1)
	@NotNull(message = "pole wymagane")
	private String username;
	@Size(min = 5, message = "hasło musi zawierać minimum 5 znaków")
	@NotNull(message = "pole wymagane")
	private String password;
	@NotNull(message = "pole wymagane")
	private String name;

	@NotNull(message = "pole wymagane")
	private String surname;
	@OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
	private List<Borrow>  borrowsUser;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
