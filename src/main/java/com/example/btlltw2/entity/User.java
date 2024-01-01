package com.example.btlltw2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String name;
	private String email;
	private String password;
	private String position;

	@OneToMany(cascade = {CascadeType.ALL})
	private List<Bill> billList;

	@OneToOne(cascade = {CascadeType.ALL},mappedBy = "user")
	private CartEntity cart;

	@OneToMany(cascade = {CascadeType.ALL})
	private List<Comment> comments;
}
