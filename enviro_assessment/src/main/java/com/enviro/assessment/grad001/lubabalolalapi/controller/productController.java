package com.enviro.assessment.grad001.lubabalolalapi.controller;

import com.enviro.assessment.grad001.lubabalolalapi.model.product;
import com.enviro.assessment.grad001.lubabalolalapi.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {
    private final productRepository productRepository;

    @Autowired
    public productController(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<product> getProductById(@PathVariable Long id) {
        product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<product>> getAllProducts() {
        List<product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }
}

