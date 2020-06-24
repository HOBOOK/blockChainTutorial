package com.example.demo.entity.transaction;

public class TransactionInput {
    public String getTransactionOutputId() {
        return transactionOutputId;
    }

    public void setTransactionOutputId(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public TransactionOutput getUTXO() {
        return UTXO;
    }

    public void setUTXO(TransactionOutput UTXO) {
        this.UTXO = UTXO;
    }

    private String transactionOutputId;
    private TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId){
        this.transactionOutputId = transactionOutputId;
    }
}
