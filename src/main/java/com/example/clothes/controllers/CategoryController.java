package com.example.clothes.controllers;

import com.example.clothes.services.Impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

}
