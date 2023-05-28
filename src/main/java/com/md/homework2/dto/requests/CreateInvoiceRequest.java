package com.md.homework2.dto.requests;

public record CreateInvoiceRequest(Double amount,
                                 String sector,
                                 Long customerId) {
}
