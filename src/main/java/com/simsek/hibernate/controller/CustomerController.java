package com.simsek.hibernate.controller;

import com.simsek.hibernate.adapter.AddressCreateRequest;
import com.simsek.hibernate.adapter.CustomerCreateRequest;
import com.simsek.hibernate.adapter.CustomerUpdateRequest;
import com.simsek.hibernate.entity.Address;
import com.simsek.hibernate.entity.Customer;
import com.simsek.hibernate.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity list() {
        List<Customer> customerList = this.customerService.list();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        Optional<Customer> customer = this.customerService.get(id);
        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @PostMapping("")
    public ResponseEntity create(@Validated @RequestBody CustomerCreateRequest customerCreateRequest) {
        Customer customer = this.customerService.create(customerCreateRequest);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("")
    public ResponseEntity update(@Validated @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        Optional<Customer> customer = this.customerService.update(customerUpdateRequest);
        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        this.customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/address")
    public ResponseEntity createAddress(@Validated @RequestBody AddressCreateRequest addressCreateRequest) {
        Optional<Address> optionalAddress = this.customerService.createAddress(addressCreateRequest);
        return ResponseEntity.ok(optionalAddress.get());
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity deleteAddress(@PathVariable String id) {
        this.customerService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
