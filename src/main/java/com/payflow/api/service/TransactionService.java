package com.payflow.api.service;

import com.payflow.api.entity.Transaction;
import com.payflow.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    // Same mechanism as UserService: Spring discovers TransactionRepository at
    // startup, builds a proxy backed by Spring Data JPA, registers it as a bean,
    // and @Autowired wires that bean into this constructor automatically.
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction sendMoney(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
