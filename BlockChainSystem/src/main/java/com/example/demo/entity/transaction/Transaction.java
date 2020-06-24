package com.example.demo.entity.transaction;

import com.example.demo.common.StringUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    private String transactionId;
    private PublicKey sender;

    public PublicKey getSender() {
        return sender;
    }

    public PublicKey getRecipient() {
        return recipient;
    }

    public float getValue() {
        return value;
    }

    private PublicKey recipient;
    private float value;

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getSignature() {
        return signature;
    }

    private byte[] signature;

    public ArrayList<TransactionInput> getInputs() {
        return inputs;
    }

    public ArrayList<TransactionOutput> getOutputs() {
        return outputs;
    }

    private ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    private ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.recipient = to;
        this.value =value;
        this.inputs = inputs;
    }
}
