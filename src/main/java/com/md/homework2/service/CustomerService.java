package com.md.homework2.service;

import com.md.homework2.base.BaseModelService;
import com.md.homework2.model.Customer;
import com.md.homework2.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseModelService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
    }
}
