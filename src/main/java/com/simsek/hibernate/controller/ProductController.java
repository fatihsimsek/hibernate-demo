package com.simsek.hibernate.controller;

import com.simsek.hibernate.adapter.ProductCreateRequest;
import com.simsek.hibernate.adapter.ProductUpdateRequest;
import com.simsek.hibernate.service.ProductService;
import com.simsek.hibernate.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity list() {
        List<Product> productList = this.productService.list();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        Optional<Product> product = this.productService.get(id);
        if(product.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @PostMapping("")
    public ResponseEntity create(@Validated @RequestBody ProductCreateRequest productCreateRequest) {
        Product product = this.productService.create(productCreateRequest);
        return ResponseEntity.ok(product);
    }

    @PutMapping("")
    public ResponseEntity update(@Validated @RequestBody ProductUpdateRequest productUpdateRequest) {
        Optional<Product> product = this.productService.update(productUpdateRequest);
        if(product.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        this.productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
