package com.example.btlltw2.controller;

import com.example.btlltw2.dto.AddCartCommand;
import com.example.btlltw2.entity.Book;
import com.example.btlltw2.entity.CartBook;
import com.example.btlltw2.entity.CartEntity;
import com.example.btlltw2.entity.User;
import com.example.btlltw2.exception.ApiException;
import com.example.btlltw2.repository.BookRepository;
import com.example.btlltw2.repository.CartBookRepository;
import com.example.btlltw2.repository.CartRepository;
import com.example.btlltw2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
        User user=userRepository.findById(addCartCommand.getUserId()).orElse(null);
        Book book =bookRepository.findById(addCartCommand.getBookId()).orElse(null);
        AtomicBoolean isNew= new AtomicBoolean(true);
        user.getCart().getCartBooks().forEach(cartBook -> {
            if(cartBook.getBook().getId()==addCartCommand.getBookId()){
                isNew.set(false);
                if(cartBook.getAmount()+addCartCommand.getAmount()<=book.getQuantity()){
                    cartBook.setAmount(cartBook.getAmount()+addCartCommand.getAmount());
                    cartBookRepository.save(cartBook);
                }else{
                    throw new ApiException(HttpStatus.BAD_REQUEST,"Bạn đã thêm số lượng tối đa của sản phẩm này vào giỏ!");
                }
            }
        });
        if(isNew.get()==true){
            user.getCart().getCartBooks().add(CartBook.builder()
                            .cart(user.getCart())
                            .amount(addCartCommand.getAmount())
                            .book(book)
                    .build());
            userRepository.save(user);
        }
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
