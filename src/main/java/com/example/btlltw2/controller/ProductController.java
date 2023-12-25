package com.example.btlltw2.controller;

import com.example.btlltw2.entity.Book;
import com.example.btlltw2.entity.Category;
import com.example.btlltw2.repository.BookRepository;
import com.example.btlltw2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/initBook")
    private void initBook(){
        Random random = new Random();

        Category category1 = new Category();
        category1.setName("SOS");
        Category category2 = new Category();
        category2.setName("SOS2");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        IntStream.rangeClosed(1, 40).forEach(i -> {
            Book book = new Book();
            book.setTitle("Book " + i);
            book.setAuthor("Author " + i);
            book.setDes("Description " + i);
            book.setDay(new Date());
            book.setCount(random.nextInt(100) + 1);
            book.setImage("/assets/book1.png");
            book.setPrice(random.nextFloat() * 100);
            book.setQuantity(random.nextInt(50) + 1);
            book.setSold(random.nextInt(20));
            book.setCategory(random.nextBoolean() ? category1 : category2);
            bookRepository.save(book);
        });
    }

}
