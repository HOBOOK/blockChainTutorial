package com.example.demo.repository;

import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.transaction.TransactionInput;
import com.example.demo.entity.transaction.TransactionOutput;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class TransactionRepository {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();

    public ArrayList<Transaction> findAllTransactions(){
        return transactions;
    }
    
    public HashMap<String, TransactionOutput> findUTXOs(){
        return UTXOs;
    }

}
