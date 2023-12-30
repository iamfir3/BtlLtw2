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
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private Date paymentTime;
	private String address;
	private String phone;
	private String nameReceiver;

	@Enumerated(EnumType.STRING)
	private BillStatus status;
	private String paymentCode;

	@OneToMany(mappedBy = "bill",cascade = {CascadeType.ALL})
	private List<ItemBill> itemBills;

	@ManyToOne
	private User user;

}
