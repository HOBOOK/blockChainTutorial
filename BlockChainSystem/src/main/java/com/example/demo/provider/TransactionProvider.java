package com.example.demo.provider;

import com.example.demo.entity.Wallet;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionProvider {
    @Autowired private TransactionRepository transactionRepository;

    public void vote(Wallet wallet){
        
    }
}
