package com.md.homework2.dto;

import java.time.LocalDateTime;

public record InvoiceDto(Long id,
                         Double amount,
                         String sector,
                         LocalDateTime amountDate,
                         Long customerId) {
}
