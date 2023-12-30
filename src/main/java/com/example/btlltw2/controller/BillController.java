package com.example.btlltw2.controller;

import com.example.btlltw2.dto.BillDTO;
import com.example.btlltw2.entity.*;
import com.example.btlltw2.exception.ApiException;
import com.example.btlltw2.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("bill")
public class BillController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ItemBillRepository itemBillRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartBookRepository cartBookRepository;


    @Transactional
    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody BillDTO billDTO){
        User user=userRepository.findById(billDTO.getUserId()).orElse(null);
        CartEntity cart=cartRepository.findByUser(user);
        List<ItemBill> itemBillList=new ArrayList<>();
        cart.getCartBooks().forEach(cartBook -> {
            ItemBill itemBill=ItemBill.builder()
                    .amount(cartBook.getAmount())
                    .price(cartBook.getBook().getPrice())
                    .book(cartBook.getBook())
                    .build();
            if(cartBook.getBook().getQuantity()<cartBook.getAmount()){
                cartBookRepository.delete(cartBook);
                throw new ApiException(HttpStatus.BAD_REQUEST,"Sản phẩm "+cartBook.getBook().getTitle()+" đã hết!");
            }else {
                cartBook.getBook().setQuantity(cartBook.getBook().getQuantity()-cartBook.getAmount());
                cartBookRepository.save(cartBook);
            }
            itemBillList.add(itemBill);
        });

            Bill bill=Bill.builder()
                .paymentTime(new Date())
                .address(billDTO.getAddress())
                .phone(billDTO.getPhone())
                .nameReceiver(billDTO.getNameReceiver())
                .status(billDTO.getStatus())
                .paymentCode(billDTO.getPaymentCode())
                .user(user)
                .itemBills(itemBillList)
                .status(BillStatus.PENDING)
                .build();
        Bill savedBill=billRepository.save(bill);
        itemBillList.forEach(itemBill -> {
            itemBill.setBill(savedBill);
        });
        itemBillRepository.saveAll(itemBillList);
        cartBookRepository.deleteAllByCart(cart);
        return ResponseEntity.ok("ok");
    }
}
