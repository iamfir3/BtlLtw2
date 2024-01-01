package com.example.btlltw2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int point;
    private String comment;
    private Date createdAt;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

}
