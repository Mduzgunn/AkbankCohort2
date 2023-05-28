package com.md.homework2.controller;

import com.md.homework2.base.RestResponse;
import com.md.homework2.controller.contract.InvoiceControllerContract;
import com.md.homework2.dto.InvoiceDto;
import com.md.homework2.dto.requests.CreateInvoiceRequest;
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
@RequestMapping("/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceControllerContract invoiceControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<InvoiceDto>> save(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
        var invoiceDto = invoiceControllerContract.save(createInvoiceRequest);
        return ResponseEntity.ok(RestResponse.of(invoiceDto));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<InvoiceDto>>> findAll() {
        var invoiceDtoList = invoiceControllerContract.findAll();
        return ResponseEntity.ok(RestResponse.of(invoiceDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<InvoiceDto>> findById(@PathVariable Long id) {
        var invoiceDto = invoiceControllerContract.findById(id);
        return ResponseEntity.ok(RestResponse.of(invoiceDto));
    }

    @GetMapping("/invoiceAbove")
    public ResponseEntity<RestResponse<List<InvoiceDto>>> findInvoicesAbove() {
        var invoiceDtoList = invoiceControllerContract.findInvoicesAbove();
        return ResponseEntity.ok(RestResponse.of(invoiceDtoList));
    }

    @GetMapping("/averageAmount")
    public ResponseEntity<RestResponse<String>> findAverageInvoicesAbove() {
        var averageAmount = invoiceControllerContract.findAverageInvoicesAbove();
        return ResponseEntity.ok(RestResponse.of(
                String.format("The average of invoices over 1500 TL in the system: %s", averageAmount)));
    }

    @GetMapping("/belowAmountCustomer")
    public ResponseEntity<RestResponse<List<String>>> findCustomersBelowAmount() {
        var nameList = invoiceControllerContract.findCustomersBelowAmount();
        return ResponseEntity.ok(RestResponse.of(nameList));
    }

    @GetMapping("/belowAmountSector")
    public ResponseEntity<RestResponse<List<String>>> findSectorBelowAmount() {
        var belowAmountList = invoiceControllerContract.findSectorBelowAmount();
        return ResponseEntity.ok(RestResponse.of(belowAmountList));
    }

}
