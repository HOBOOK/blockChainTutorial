package com.example.demo.repository;

import com.example.demo.entity.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TransactionRepository {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    public ArrayList<Transaction> findAllTransactions(){
        return transactions;
    }
}
