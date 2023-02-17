package com.simsek.hibernate.repository;

import com.simsek.hibernate.adapter.OrderItemCreateRequest;
import com.simsek.hibernate.entity.Order;
import com.simsek.hibernate.entity.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {
    private EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Order> list() {
        TypedQuery<Order> query = entityManager.createQuery("from Order", Order.class);
        return query.getResultList();
    }

    public Optional<Order> get(String id) {
        Order order = entityManager.find(Order.class, UUID.fromString(id));
        return Optional.ofNullable(order);
    }

    public Optional<OrderItem> getOrderItem(String id) {
        OrderItem orderItem = entityManager.find(OrderItem.class, UUID.fromString(id));
        return Optional.ofNullable(orderItem);
    }

    @Transactional
    public Order save(Order order) {
        if(order.getId() != null) {
            entityManager.merge(order);
        } else {
            entityManager.persist(order);
        }
        return order;
    }

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        if(orderItem.getId() != null) {
            entityManager.merge(orderItem);
        } else {
            entityManager.persist(orderItem);
        }
        return orderItem;
    }

    @Transactional
    public void deleteOrderItem(OrderItem orderItem) {
        entityManager.remove(orderItem);
    }
}
