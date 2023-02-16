package com.simsek.hibernate.service;

import com.simsek.hibernate.adapter.ProductCreateRequest;
import com.simsek.hibernate.adapter.ProductUpdateRequest;
import com.simsek.hibernate.entity.Product;
import com.simsek.hibernate.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list() {
        return this.productRepository.list();
    }

    public Optional<Product> get(String id) {
        return this.productRepository.get(id);
    }

    public Product create(ProductCreateRequest productCreateRequest) {
        Product product = new Product(productCreateRequest.getCode(), productCreateRequest.getDescription(), productCreateRequest.getAmount());
        return this.productRepository.save(product);
    }

    public Optional<Product> update(ProductUpdateRequest productUpdateRequest) {
        Optional<Product> optionalProduct = get(productUpdateRequest.getId());

        if(optionalProduct.isEmpty())
            return Optional.empty();

        Product product = optionalProduct.get();
        product.setCode(productUpdateRequest.getCode());
        product.setDescription(productUpdateRequest.getDescription());
        product.setAmount(productUpdateRequest.getAmount());

        return Optional.of(this.productRepository.save(product));
    }

    public void delete(String id) {
        this.productRepository.delete(id);
    }
}
