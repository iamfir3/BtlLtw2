package com.example.btlltw2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String title;
	private String author;
	private String des;
	private Date day;
	private String image;
	private int price;
	private int quantity;
	private int sold;


	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "book")
	private List<Comment> comments;

	@OneToMany(mappedBy = "book",cascade = {CascadeType.ALL})
	private List<ItemBill> itemBill;

	@OneToMany(mappedBy = "book",cascade = {CascadeType.ALL})
	private List<CartBook> cartBooks;

}
