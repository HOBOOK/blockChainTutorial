package com.example.demo.entity.transaction;

public class TransactionInput {
    private String transactionOutputId;
    private TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId){
        this.transactionOutputId = transactionOutputId;
    }
}
