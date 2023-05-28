package com.md.homework2.controller.contract;

import com.md.homework2.dto.CustomerDto;
import com.md.homework2.dto.requests.CreateCustomerRequest;

import java.util.List;

public interface CustomerControllerContract {

    CustomerDto save(CreateCustomerRequest createCustomerRequest);

    List<CustomerDto> findAll();

    CustomerDto findById(Long id);

    List<CustomerDto> findByNameIncludeC();

    Double getCustomersInvoiceCreatedJune();
}
