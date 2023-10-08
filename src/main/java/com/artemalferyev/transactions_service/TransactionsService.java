package com.artemalferyev.transactions_service;

import com.artemalferyev.transactions_service.model.TransferBalance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionsService {

    private final BalanceRepository repository;

    public BigDecimal getBalance(Long accountId){
        BigDecimal balance = repository.getBalanceForId(accountId);
        if (balance == null) {
            throw new IllegalArgumentException();
        }
        return balance;
    }

    public BigDecimal addMoney(Long recipient, BigDecimal amount) {
        BigDecimal currentBalance = repository.getBalanceForId(recipient);
        if (currentBalance == null) {
            repository.save(recipient, amount);
            return amount;
        } else {
            final BigDecimal updatedBalane = currentBalance.add(amount);
            repository.save(recipient, updatedBalane);
            return updatedBalane;
        }
    }

    public void makeTransfer(TransferBalance transferBalance){
        BigDecimal fromBalance = repository.getBalanceForId(transferBalance.getSender());
        BigDecimal toBalance = repository.getBalanceForId(transferBalance.getRecipient());
        if (fromBalance == null || toBalance == null) throw new IllegalArgumentException("no ids");
        if (transferBalance.getAmount().compareTo(fromBalance) > 0) throw new IllegalArgumentException("no funds");

        BigDecimal updatedFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updatedToBalance = toBalance.add(transferBalance.getAmount());
        repository.save(transferBalance.getRecipient(), updatedFromBalance);
        repository.save(transferBalance.getSender(), updatedToBalance);
        }
    }
