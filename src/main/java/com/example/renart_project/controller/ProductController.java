package com.example.renart_project.controller;


import com.example.renart_project.dto.ProductResponse;
import com.example.renart_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "https://renart-page-frontend.vercel.app"})
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getProducts(){
        return productService.getAllProducts();
    }
}
