package com.md.homework2.controller.contract.impl;

import com.md.homework2.controller.contract.InvoiceControllerContract;
import com.md.homework2.dto.InvoiceDto;
import com.md.homework2.dto.requests.CreateInvoiceRequest;
import com.md.homework2.mapper.InvoiceMapper;
import com.md.homework2.model.Customer;
import com.md.homework2.model.Invoice;
import com.md.homework2.service.CustomerService;
import com.md.homework2.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceControllerContractImpl implements InvoiceControllerContract {

    private final InvoiceService invoiceService;

    private final CustomerService customerService;

    @Override
    public InvoiceDto save(CreateInvoiceRequest createInvoiceRequest) {
        Customer customer = customerService.findByIdWithControl(createInvoiceRequest.customerId());

        Invoice invoice = InvoiceMapper.INSTANCE.convertToInvoice(createInvoiceRequest);
        invoice.setAmountDate(LocalDateTime.now());
        invoice.setCustomer(customer);

        invoice = invoiceService.save(invoice);

        return InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice);
    }

    @Override
    public List<InvoiceDto> findAll() {
        List<Invoice> invoiceList = invoiceService.findAll();
        return InvoiceMapper.INSTANCE.convertToInvoiceDTOList(invoiceList);
    }

    @Override
    public InvoiceDto findById(Long id) {
        Invoice invoice = invoiceService.findByIdWithControl(id);
        return InvoiceMapper.INSTANCE.convertToInvoiceDTO(invoice);
    }

    @Override
    public List<InvoiceDto> findInvoicesAbove() {
        double threshold = 1500;
        List<Invoice> invoiceList = invoiceService.findAll();

        return invoiceList.stream()
                .filter(invoice -> invoice.getAmount() > threshold)
                .map(InvoiceMapper.INSTANCE::convertToInvoiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double findAverageInvoicesAbove() {
        double threshold = 1500;
        List<Invoice> invoiceList = invoiceService.findAll();

        return invoiceList.stream()
                .filter(invoice -> invoice.getAmount() > threshold)
                .mapToDouble(Invoice::getAmount)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<String> findCustomersBelowAmount() {
        double threshold = 500;
        List<Customer> customerList = customerService.findAll();
        return customerList.stream()
                .flatMap(customer -> customer.getInvoiceList().stream())
                .filter(invoice -> invoice.getAmount() < threshold)
                .map(Invoice::getCustomer)
                .map(Customer::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findSectorBelowAmount() {
        double threshold = 750;
        return invoiceService.findSectorByAverageAmountLessThan(threshold);
    }

}
