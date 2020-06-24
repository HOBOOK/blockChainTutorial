package com.example.demo.controller;

import com.example.demo.entity.Wallet;
import com.example.demo.provider.TransactionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired private TransactionProvider transactionProvider;
    @PostMapping
    public void vote(@RequestBody Wallet wallet){
        transactionProvider.vote(wallet);
    }
}
