package com.artemalferyev.transactions_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionsServiceTest {

    private BalanceRepository balanceRepository = new BalanceRepository();
    private TransactionsService transactionsService = new TransactionsService(balanceRepository);
    private final Long USER_ID = 1L;
    private BigDecimal initialBalance;

    @BeforeEach
    void setUp() {
        balanceRepository = mock(BalanceRepository.class);
        transactionsService = new TransactionsService(balanceRepository);

        initialBalance = BigDecimal.TEN; // Starting with 10 as the initial balance
        when(balanceRepository.getBalanceForId(USER_ID)).thenReturn(initialBalance);
    }

    @Test
    void getBalance() {
        final BigDecimal balance = transactionsService.getBalance(USER_ID);
        assertEquals(initialBalance, balance);
    }

    @Test
    void addMoney() {
        BigDecimal amountToAdd = BigDecimal.ONE;
        BigDecimal expectedBalanceAfterAddition = initialBalance.add(amountToAdd);

        when(balanceRepository.save(USER_ID, amountToAdd)).thenReturn(expectedBalanceAfterAddition);

        final BigDecimal balance = transactionsService.addMoney(USER_ID, amountToAdd);

        assertEquals(expectedBalanceAfterAddition, balance);
    }
}