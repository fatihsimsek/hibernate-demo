package com.simsek.hibernate.repository;

import com.simsek.hibernate.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepository {
    private EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> list() {
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    public Optional<Product> get(String id) {
        Product product = entityManager.find(Product.class, UUID.fromString(id));
        return Optional.ofNullable(product);
    }

    @Transactional
    public Product save(Product product) {
        if(product.getId() != null) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
        return product;
    }

    @Transactional
    public void delete(String id) {
        Optional<Product> product = get(id);
        if(product.isPresent()) {
            entityManager.remove(product.get());
        }
    }
}
