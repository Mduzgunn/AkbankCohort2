package com.md.homework2.mapper;

import com.md.homework2.dto.InvoiceDto;
import com.md.homework2.dto.requests.CreateInvoiceRequest;
import com.md.homework2.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    Invoice convertToInvoice(CreateInvoiceRequest createInvoiceRequest);

    @Mapping(target = "customerId", source = "customer.id")
    InvoiceDto convertToInvoiceDTO(Invoice invoice);

    List<InvoiceDto> convertToInvoiceDTOList (List<Invoice> invoiceList);

}
