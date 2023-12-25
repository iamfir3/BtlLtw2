package com.example.btlltw2.controller;

import com.example.btlltw2.dto.AddCartCommand;
import com.example.btlltw2.entity.Book;
import com.example.btlltw2.entity.CartBook;
import com.example.btlltw2.entity.CartEntity;
import com.example.btlltw2.entity.User;
import com.example.btlltw2.repository.BookRepository;
import com.example.btlltw2.repository.CartBookRepository;
import com.example.btlltw2.repository.CartRepository;
import com.example.btlltw2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartItem")
public class CartController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartBookRepository cartBookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    private ResponseEntity<?> addCart(@RequestBody AddCartCommand addCartCommand){
        Optional<User> user=userRepository.findById(addCartCommand.getUserId());
        Optional<Book> book =bookRepository.findById(addCartCommand.getBookId());

        CartBook cartBook=new CartBook();
        cartBook.setBook(book.get());
        cartBook.setAmount(addCartCommand.getAmount());
        cartBook.setCart(user.get().getCart());
        cartBookRepository.save(cartBook);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping
    private ResponseEntity<?> deleteCart(@RequestParam("id") Long id){
        Optional<CartBook> cartBook=cartBookRepository.findById(id);
        cartBookRepository.delete(cartBook.get());
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/payment")
    private ResponseEntity<?> payCart(@RequestParam("id") Long id){
        Optional<CartEntity> cart=cartRepository.findById(id);
        cartBookRepository.deleteAllByCart(cart.get());
        return ResponseEntity.ok("Success");
    }
}
