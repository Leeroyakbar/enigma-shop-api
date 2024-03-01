package com.enigma.wmb_api.service;

import com.enigma.wmb_api.dto.request.SearchCustomerRequest;
import com.enigma.wmb_api.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Customer create(Customer customer);

    Page<Customer> getAll(SearchCustomerRequest request);

    Customer getById(String id);

    Customer update(Customer customer);

    void delete(String id);
}
