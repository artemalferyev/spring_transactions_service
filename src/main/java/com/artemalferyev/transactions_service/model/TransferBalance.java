package com.artemalferyev.transactions_service.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBalance {

    private Long sender;
    private Long recipient;
    private BigDecimal amount;
}
