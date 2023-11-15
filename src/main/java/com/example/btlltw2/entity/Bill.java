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
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private Date paymentTime;
	private String address;
	private String phone;
	private String payment;
	private int totalAmount;
	private float totalPrice;

	@OneToMany
	private List<ItemBill> itemBills;

	@ManyToOne
	private User user;

}
