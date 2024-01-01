package com.example.btlltw2.repository;

import com.example.btlltw2.entity.Book;
import com.example.btlltw2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByBook(Book book);
}
