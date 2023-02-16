package com.simsek.hibernate.service;

import com.simsek.hibernate.adapter.OrderCreateRequest;
import com.simsek.hibernate.adapter.OrderItemCreateRequest;
import com.simsek.hibernate.entity.Customer;
import com.simsek.hibernate.entity.Order;
import com.simsek.hibernate.entity.OrderItem;
import com.simsek.hibernate.repository.CustomerRepository;
import com.simsek.hibernate.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> list() {
        return this.orderRepository.list();
    }

    public Optional<Order> get(String id) {
        return this.orderRepository.get(id);
    }

    public Optional<Order> create(OrderCreateRequest orderCreateRequest) {
        Optional<Customer> optionalCustomer = this.customerRepository.get(orderCreateRequest.getCustomerId());

        if(optionalCustomer.isEmpty())
            return Optional.empty();

        Order order = new Order(LocalDateTime.now());
        order.setCustomer(optionalCustomer.get());

        return Optional.of(this.orderRepository.save(order));
    }

    public Optional<OrderItem> createOrderItem(OrderItemCreateRequest orderItemCreateRequest) {
        return null;
    }

    public void deleteOrderItem(String id) {
    }
}
