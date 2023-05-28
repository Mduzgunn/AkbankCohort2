package com.md.homework2.controller.contract.impl;

import com.md.homework2.controller.contract.CustomerControllerContract;
import com.md.homework2.dto.CustomerDto;
import com.md.homework2.dto.requests.CreateCustomerRequest;
import com.md.homework2.mapper.CustomerMapper;
import com.md.homework2.model.Customer;
import com.md.homework2.model.Invoice;
import com.md.homework2.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerControllerContractImpl implements CustomerControllerContract {

    private final CustomerService customerService;

    @Override
    public CustomerDto save(CreateCustomerRequest createCustomerRequest) {
        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(createCustomerRequest);
        customer = customerService.save(customer);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerService.findAll();
        return customerList.stream()
                .map(CustomerMapper.INSTANCE::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerService.findByIdWithControl(id);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDto> findByNameIncludeC() {
        List<Customer> customerList = customerService.findAll();
        return customerList.stream()
                .filter(customer -> customer.getName().toUpperCase().contains("C")
                        || customer.getSurname().toUpperCase().contains("C"))
                .map(CustomerMapper.INSTANCE::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getCustomersInvoiceCreatedJune() {
        List<Customer> customerList = customerService.findAll();
        LocalDate juneStart = LocalDate.of(LocalDate.now().getYear(), Month.JUNE, 1);
        LocalDate juneEnd = LocalDate.of(LocalDate.now().getYear(), Month.JUNE, 30);
        return customerList.stream()
                .filter(customer -> customer.getBaseAdditionalFields().getCreateDate().toLocalDate().isAfter(juneStart)
                        && customer.getBaseAdditionalFields().getCreateDate().toLocalDate().isBefore(juneEnd))
                .flatMap(customer -> customer.getInvoiceList().stream())
                .mapToDouble(Invoice::getAmount)
                .sum();
    }
}
