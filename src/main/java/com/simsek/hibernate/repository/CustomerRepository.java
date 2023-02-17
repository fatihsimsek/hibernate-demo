package com.simsek.hibernate.repository;

import com.simsek.hibernate.entity.Address;
import com.simsek.hibernate.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository {
    private EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Customer> list() {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer", Customer.class);
        return query.getResultList();
    }

    public Optional<Customer> get(String id) {
        Customer customer = entityManager.find(Customer.class, UUID.fromString(id));
        return Optional.ofNullable(customer);
    }

    public Optional<Address> getAddress(String id) {
        Address address = entityManager.find(Address.class, UUID.fromString(id));
        return Optional.ofNullable(address);
    }

    @Transactional
    public Customer save(Customer customer) {
        if(customer.getId() != null) {
            entityManager.merge(customer);
        } else {
            entityManager.persist(customer);
        }
        return customer;
    }

    public void delete(String id) {
        Optional<Customer> customer = get(id);
        if(customer.isPresent()) {
            entityManager.remove(customer.get());
        }
    }

    @Transactional
    public Address saveAddress(Address address) {
        if(address.getId() != null) {
            entityManager.merge(address);
        } else {
            entityManager.persist(address);
        }
        return address;
    }

    public void deleteAddress(Address address) {
        entityManager.remove(address);
    }
}
