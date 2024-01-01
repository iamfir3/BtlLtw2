package com.example.btlltw2.controller;

import com.example.btlltw2.dto.CategoryDTO;
import com.example.btlltw2.entity.Category;
import com.example.btlltw2.exception.ApiException;
import com.example.btlltw2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
        Category check=categoryRepository.findByName(categoryDTO.getName());
        if(check!=null)
        {
            throw new ApiException(HttpStatus.BAD_REQUEST,"Existed category");
        }
        Category category=Category.builder()
                .name(categoryDTO.getName())
                .build();
        categoryRepository.save(category);
        return ResponseEntity.ok("ok");
    }
}
