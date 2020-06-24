package com.example.demo.entity.transaction;

import com.example.demo.common.StringUtil;

import java.security.PublicKey;

public class TransactionOutput {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PublicKey getRecipient() {
        return recipient;
    }

    public void setRecipient(PublicKey recipient) {
        this.recipient = recipient;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    private PublicKey recipient;
    private float value;
    private String parentTransactionId;

    public TransactionOutput(PublicKey recipient, float value, String parentTransactionId){
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.getSha256(StringUtil.getStringFromKey(recipient) + Float.toString(value) + parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey){
        return (publicKey == recipient);
    }
}
