package com.artemalferyev.transactions_service;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BalanceRepository {

    private final Map<Long, BigDecimal> storage = new HashMap<>(Map.of(1L, BigDecimal.ZERO));

    public BigDecimal getBalanceForId(Long accountId){
        return storage.get(accountId);
    }

    public BigDecimal save(Long id, BigDecimal amount) {
        return storage.put(id, amount);
    }
}
