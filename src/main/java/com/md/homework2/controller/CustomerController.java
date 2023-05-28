package com.md.homework2.controller;


import com.md.homework2.base.RestResponse;
import com.md.homework2.controller.contract.CustomerControllerContract;
import com.md.homework2.dto.CustomerDto;
import com.md.homework2.dto.requests.CreateCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerControllerContract customerControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<CustomerDto>> save(@RequestBody CreateCustomerRequest createCustomerRequest) {
        var customerDTO = customerControllerContract.save(createCustomerRequest);
        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerDto>>> findAll() {
        var customerDTOList = customerControllerContract.findAll();
        return ResponseEntity.ok(RestResponse.of(customerDTOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerDto>> findById(@PathVariable Long id) {
        var customerDTO = customerControllerContract.findById(id);
        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }

    @GetMapping("/c")
    public ResponseEntity<RestResponse<List<CustomerDto>>> findByNameIncludeC() {
        var customerDTOList = customerControllerContract.findByNameIncludeC();
        return ResponseEntity.ok(RestResponse.of(customerDTOList));
    }

    @GetMapping("/june")
    public ResponseEntity<RestResponse<String>> getCustomersInvoiceCreatedJune() {
        Double totalAmount = customerControllerContract.getCustomersInvoiceCreatedJune();
        return ResponseEntity.ok(RestResponse.of(
                String.format("The total amount of invoices for customers who signed up in June => %s",totalAmount)));
    }

}
