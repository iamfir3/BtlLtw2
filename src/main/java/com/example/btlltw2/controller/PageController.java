package com.example.btlltw2.controller;

import com.example.btlltw2.dto.GetCartResponse;
import com.example.btlltw2.entity.*;
import com.example.btlltw2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartBookRepository cartBookRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ItemBillRepository itemBillRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Book> bookList = bookRepository.findAll().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("books", bookList);
        return "pages/home";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        List<Book> bookList = bookRepository.findAll().stream().limit(4).collect(Collectors.toList());
        List<Comment> comments=commentRepository.findAllByBook(book.get());
        model.addAttribute("book", book.get());
        model.addAttribute("books", bookList);
        model.addAttribute("comments",comments);
        return "pages/detail";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("books", bookList);
        return "pages/list";
    }

    @GetMapping("/cart")
    public String cart(Model model, @RequestParam("id") Long id) {
        Optional<CartEntity> cart = cartRepository.findByUserId(id);
        List<CartBook> cartBooks = cartBookRepository.findAllByCartId(cart.get().getId());
        List<GetCartResponse> getCartResponses = new ArrayList<>();
        AtomicReference<Float> subprice = new AtomicReference<>(0f);
        cartBooks.forEach(cartBook -> {
            GetCartResponse getCartResponse = GetCartResponse.builder()
                    .amount(cartBook.getAmount())
                    .title(cartBook.getBook().getTitle())
                    .image(cartBook.getBook().getImage())
                    .price(cartBook.getBook().getPrice())
                    .id(cartBook.getId())
                    .build();
            subprice.updateAndGet(v -> v + cartBook.getAmount() * cartBook.getBook().getPrice());

            getCartResponses.add(getCartResponse);
        });
        model.addAttribute("cartBooks", getCartResponses);
        model.addAttribute("subprice", subprice.get());
        model.addAttribute("cart", cart.get());
        return "pages/Cart";
    }

    @GetMapping("/login")
    private String login(Model model) {
        return "pages/login";
    }

    @GetMapping("/payment")
    private String payment(Model model, @RequestParam("id") Long id) {
        Optional<CartEntity> cart = cartRepository.findByUserId(id);
        List<CartBook> cartBooks = cartBookRepository.findAllByCartId(cart.get().getId());
        List<GetCartResponse> getCartResponses = new ArrayList<>();
        AtomicReference<Float> subprice = new AtomicReference<>(0f);
        cartBooks.forEach(cartBook -> {
            GetCartResponse getCartResponse = GetCartResponse.builder()
                    .amount(cartBook.getAmount())
                    .title(cartBook.getBook().getTitle())
                    .image(cartBook.getBook().getImage())
                    .price(cartBook.getBook().getPrice())
                    .id(cartBook.getId())
                    .build();
            subprice.updateAndGet(v -> v + cartBook.getAmount() * cartBook.getBook().getPrice());

            getCartResponses.add(getCartResponse);
        });
        model.addAttribute("cartBooks", getCartResponses);
        model.addAttribute("subprice", subprice.get());
        model.addAttribute("cart", cart.get());
        return "pages/checkout";
    }

    @GetMapping("/signup")
    private String signup(Model model) {
        return "pages/SignUp";
    }

    @GetMapping("/admin")
    private String admin(Model model) {
        List<Bill> billList = billRepository.findTop10ByOrderByPaymentTimeDesc();
        model.addAttribute("bills", billList);
        return "pages/admin/index";
    }

    @GetMapping("/bill_admin")
    private String bill(Model model) {
        List<Bill> billList = billRepository.findTop10ByOrderByPaymentTimeDesc();
        model.addAttribute("bills", billList);
        return "pages/admin2/bill_admin";
    }

    @GetMapping("/bill_detail")
    private String billDetail(Model model,@RequestParam Long billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        model.addAttribute("bill", bill);
        return "pages/admin2/bill_detail";
    }

    @GetMapping("/product_admin")
    private String product_admin(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "pages/admin/product_admin";
    }

    @GetMapping("/product_form")
    private String product_form(@RequestParam String action, @RequestParam Long id, Model model) {
        if ("view-product".equals(action)) {
            Book book = bookRepository.findById(id).orElse(null);
            model.addAttribute("book", book);
            model.addAttribute("scrum", book.getTitle());
            return "pages/admin/view_product_form";
        }
        if ("edit-product".equals(action)) {
            Book book = bookRepository.findById(id).orElse(null);
            model.addAttribute("book", book);
            model.addAttribute("scrum", book.getTitle());
            return "pages/admin/edit_product_form";
        }
        if ("add-product".equals(action)) {
            model.addAttribute("scrum", "Add product");
            List<Category> categories=categoryRepository.findAll();
            model.addAttribute("categories",categories);
            return "pages/admin/product_form";
        }
        return "redirect:/admin";
    }

    @GetMapping("/category_form")
    private String category_form(@RequestParam String action, @RequestParam Long id, Model model) {
        if ("view-category".equals(action) || "edit-category".equals(action)) {
            Category category = categoryRepository.findById(id).orElse(null);
            model.addAttribute("category", category);
            model.addAttribute("scrum", category.getName());
            return "pages/admin/category_form";
        } else if ("add-category".equals(action)) {
            model.addAttribute("scrum", "Add category");
            return "pages/admin/add_category_form";
        }
        return "redirect:/admin";
    }

    @GetMapping("/profile")
    private String profile(Model model,@RequestParam Long id){
        User user=userRepository.findById(id).orElse(null);
        model.addAttribute("user",user);
        return "pages/profile/index";
    }

    @GetMapping("/changePassword")
    private String changePassword(Model model,@RequestParam Long id){
        User user=userRepository.findById(id).orElse(null);
        model.addAttribute("user",user);
        return "pages/profile/changepassword";
    }

    @GetMapping("/userOrders")
    private String userOrders(Model model,@RequestParam Long id){
        User user=userRepository.findById(id).orElse(null);
        model.addAttribute("user",user);
        List<Bill> billList=billRepository.findAllByUser(user);
        model.addAttribute("bills",billList);
        return "pages/profile/orders";
    }

    @GetMapping("/userLists")
    private String userLists(Model model,@RequestParam Long id){
        User user=userRepository.findById(id).orElse(null);
        model.addAttribute("user",user);
        return "pages/profile/lists";
    }


    @GetMapping("/voteBook")
    private String voteBook(Model model,@RequestParam Long id){
        User user=userRepository.findById(id).orElse(null);
        List<ItemBill> itemBillList = itemBillRepository.findByBill_UserAndIsVotedFalse(user);
        model.addAttribute("items",itemBillList );
        return "pages/profile/vote";
    }
}
