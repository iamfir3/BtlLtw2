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
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "cart")
    private List<CartBook> cartBooks;
}
