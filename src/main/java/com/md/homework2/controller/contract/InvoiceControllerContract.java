package com.md.homework2.controller.contract;

import com.md.homework2.dto.InvoiceDto;
import com.md.homework2.dto.requests.CreateInvoiceRequest;

import java.util.List;

public interface InvoiceControllerContract {

    InvoiceDto save(CreateInvoiceRequest createInvoiceRequest);

    List<InvoiceDto> findAll();

    InvoiceDto findById(Long id);

    List<InvoiceDto> findInvoicesAbove();

    Double findAverageInvoicesAbove();

    List<String> findCustomersBelowAmount();

    List<String> findSectorBelowAmount();
}
