package com.artemalferyev.transactions_service;

import com.artemalferyev.transactions_service.model.TransferBalance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Slf4j
@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private final TransactionsService transactionsService;

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) {
        return transactionsService.getBalance(accountId);
    }


    @PostMapping("/add")
    public BigDecimal addMoney (@RequestBody TransferBalance transferBalance){
        return transactionsService.addMoney(transferBalance.getRecipient(), transferBalance.getAmount());
    }

    @PostMapping("/transfer")
    public void transfer (@RequestBody TransferBalance transferBalance ){
        transactionsService.makeTransfer(transferBalance);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return "Error";
    }
}
