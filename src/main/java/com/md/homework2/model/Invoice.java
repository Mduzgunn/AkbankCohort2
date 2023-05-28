package com.md.homework2.model;

import com.md.homework2.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Invoice extends BaseEntity {

    @Id
    @GeneratedValue(generator = "Customer", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Customer", sequenceName = "CUSTOMER_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "AMOUNTDATE")
    private LocalDateTime amountDate;

    @Column(name = "SECTOR", length = 50)
    private String sector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUSTOMER", nullable = false)
    private Customer customer;
}
