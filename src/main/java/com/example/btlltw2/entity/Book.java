package com.example.btlltw2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String title;
	private String author;
	private String des;
	private Date day;
	private int count;
	private String image;
	private float price;
	private int quantity;
	private int sold;

	@ManyToOne
	private Category category;

	@OneToMany
	private List<ItemBill> itemBill;

}
