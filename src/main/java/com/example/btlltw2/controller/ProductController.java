package com.example.btlltw2.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.btlltw2.dto.ProductDTO;
import com.example.btlltw2.entity.Book;
import com.example.btlltw2.entity.Category;
import com.example.btlltw2.exception.ApiException;
import com.example.btlltw2.repository.BookRepository;
import com.example.btlltw2.repository.CategoryRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
public class ProductController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Cloudinary cloudinary;

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
            book.setImage("/assets/book1.png");
            book.setPrice(random.nextInt() * 100);
            book.setQuantity(random.nextInt(50) + 1);
            book.setSold(random.nextInt(20));
            book.setCategory(random.nextBoolean() ? category1 : category2);
            bookRepository.save(book);
        });
    }

    @PostMapping("/addBook")
    private ResponseEntity<?> addProduct(@RequestParam MultipartFile file, @ModelAttribute("title") String title, @ModelAttribute("categoryId") Long categoryId, @ModelAttribute("author") String author,@ModelAttribute("date") String date, @ModelAttribute("quantity") int quantity, @ModelAttribute("price") int price, @ModelAttribute("des")String des) throws IOException, ParseException {
        Book check=bookRepository.findByTitle(title);
        if(check!=null){
            throw new ApiException(HttpStatus.BAD_REQUEST,"Existed book");
        }
        Map result = null;
            result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("secure_url");
            Category category=categoryRepository.findById(categoryId).orElse(null);
        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        Book book= Book.builder()
                .image(imageUrl)
                .title(title)
                .author(author)
                .day(parsedDate)
                .des(des)
                .category(category)
                .quantity(quantity)
                .price(price)
                .build();
        bookRepository.save(book);
        return ResponseEntity.ok("ok");
    }

}
