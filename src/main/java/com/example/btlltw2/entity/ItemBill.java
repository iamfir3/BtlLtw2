package com.example.btlltw2.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ItemBill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private int amount;
	private int price;
	private boolean isVoted;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Bill bill;
}
