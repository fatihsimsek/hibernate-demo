package com.simsek.hibernate.controller;

import com.simsek.hibernate.adapter.*;
import com.simsek.hibernate.entity.Address;
import com.simsek.hibernate.entity.Customer;
import com.simsek.hibernate.entity.Order;
import com.simsek.hibernate.entity.OrderItem;
import com.simsek.hibernate.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity list() {
        List<Order> orderList = this.orderService.list();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        Optional<Order> order = this.orderService.get(id);
        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order.get());
    }

    @PostMapping("")
    public ResponseEntity create(@Validated @RequestBody OrderCreateRequest orderCreateRequest) {
        Optional<Order> order = this.orderService.create(orderCreateRequest);
        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order.get());
    }

    @PostMapping("/item")
    public ResponseEntity createOrderItem(@Validated @RequestBody OrderItemCreateRequest orderItemCreateRequest) {
        Optional<OrderItem> optionalOrderItem = this.orderService.createOrderItem(orderItemCreateRequest);
        return ResponseEntity.ok(optionalOrderItem.get());
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteOrderItem(@PathVariable String id) {
        this.orderService.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}
