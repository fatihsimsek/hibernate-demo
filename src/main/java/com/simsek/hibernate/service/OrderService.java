package com.simsek.hibernate.service;

import com.simsek.hibernate.adapter.OrderCreateRequest;
import com.simsek.hibernate.adapter.OrderItemCreateRequest;
import com.simsek.hibernate.entity.*;
import com.simsek.hibernate.repository.CustomerRepository;
import com.simsek.hibernate.repository.OrderRepository;
import com.simsek.hibernate.repository.ProductRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
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

    @Transactional
    public Optional<OrderItem> createOrderItem(OrderItemCreateRequest orderItemCreateRequest) {
        Optional<Order> optionalOrder = this.orderRepository.get(orderItemCreateRequest.getOrderId());
        Optional<Product> optionalProduct = this.productRepository.get(orderItemCreateRequest.getProductId());

        if(optionalOrder.isPresent() && optionalProduct.isPresent()) {
            Integer quantity = orderItemCreateRequest.getQuantity();
            BigDecimal totalAmount = optionalProduct.get().getAmount().multiply(new BigDecimal(quantity));

            OrderItem orderItem = new OrderItem(LocalDateTime.now(), quantity, totalAmount, OrderStatus.INITIALIZED.name());

            Order order = optionalOrder.get();
            order.setTotalAmount(order.getTotalAmount().add(orderItem.getAmount()));

            orderItem.setOrder(order);
            orderItem.setProduct(optionalProduct.get());

            this.orderRepository.save(order);
            return Optional.of(this.orderRepository.saveOrderItem(orderItem));
        }
        return Optional.empty();
    }

    @Transactional
    public void deleteOrderItem(String id) {
        Optional<OrderItem> optionalOrderItem = this.orderRepository.getOrderItem(id);
        if(optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            orderItem.getOrder().setTotalAmount(orderItem.getOrder().getTotalAmount().subtract(orderItem.getAmount()));

            this.orderRepository.save(orderItem.getOrder());
            this.orderRepository.deleteOrderItem(orderItem);
        }
    }
}
