package com.simsek.hibernate.service;

import com.simsek.hibernate.adapter.AddressCreateRequest;
import com.simsek.hibernate.adapter.CustomerCreateRequest;
import com.simsek.hibernate.adapter.CustomerUpdateRequest;
import com.simsek.hibernate.entity.Address;
import com.simsek.hibernate.entity.Customer;
import com.simsek.hibernate.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final DateTimeFormatter birthDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> list() {
        return this.customerRepository.list();
    }

    public Optional<Customer> get(String id) {
        return this.customerRepository.get(id);
    }

    public Customer create(CustomerCreateRequest customerCreateRequest) {
        Customer customer = new Customer(customerCreateRequest.getFullname(), LocalDate.parse(customerCreateRequest.getBirthDate(),birthDateFormatter));
        return this.customerRepository.save(customer);
    }

    public Optional<Customer> update(CustomerUpdateRequest customerUpdateRequest) {
        Optional<Customer> optionalCustomer = get(customerUpdateRequest.getId());

        if(optionalCustomer.isEmpty())
            return Optional.empty();

        Customer customer = optionalCustomer.get();
        customer.setFullname(customerUpdateRequest.getFullname());
        customer.setBirthDate(LocalDate.parse(customerUpdateRequest.getBirthDate(),birthDateFormatter));

        return Optional.of(this.customerRepository.save(customer));
    }

    public void delete(String id) {
        this.customerRepository.delete(id);
    }

    @Transactional
    public Optional<Address> createAddress(AddressCreateRequest addressCreateRequest) {
        Optional<Customer> optionalCustomer = get(addressCreateRequest.getCustomerId());
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Address address = new Address(addressCreateRequest.getCity(), addressCreateRequest.getDistrict(), addressCreateRequest.getDescription(), customer);

            if(addressCreateRequest.isBillingAddress() || addressCreateRequest.isContactAddress()){
                if(addressCreateRequest.isBillingAddress())
                    customer.setBillingAddress(address);
                if(addressCreateRequest.isContactAddress())
                    customer.setContactAddress(address);
                this.customerRepository.save(customer);
            } else {
                this.customerRepository.saveAddress(address);
            }
            return Optional.of(address);
        }
        return Optional.empty();
    }

    @Transactional
    public void deleteAddress(String id) {
        Optional<Address> optionalAddress = this.customerRepository.getAddress(id);
        if(optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            Customer customer = address.getCustomer();

            if(customer.getBillingAddress() != null && customer.getBillingAddress().getId().toString().equals(id)) {
                customer.setBillingAddress(null);
            }

            if(customer.getContactAddress() != null && customer.getContactAddress().getId().toString().equals(id)) {
                customer.setContactAddress(null);
            }

            this.customerRepository.save(customer);
            this.customerRepository.deleteAddress(address);
        }
    }
}
