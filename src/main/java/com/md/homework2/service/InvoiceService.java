package com.md.homework2.service;

import com.md.homework2.base.BaseModelService;
import com.md.homework2.model.Invoice;
import com.md.homework2.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService extends BaseModelService<Invoice, InvoiceRepository> {

    public InvoiceService(InvoiceRepository invoiceRepository) {
        super(invoiceRepository);
    }

    public List<String> findSectorByAverageAmountLessThan(double threshold) {
        return getRepository().findSectorsWithAverageAmountLessThan(threshold);
    }

}
