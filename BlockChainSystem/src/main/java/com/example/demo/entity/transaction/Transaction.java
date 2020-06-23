package com.example.demo.entity.transaction;

import com.example.demo.common.StringUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {

    private String transactionId;
    private PublicKey sender;
    private PublicKey recipient;
    private float value;
    private byte[] signature;

    private ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    private ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0;

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.recipient = to;
        this.value =value;
        this.inputs = inputs;
    }

    private String makeHashBlock(){
        sequence++;
        return StringUtil.getSha256(
                StringUtil.getStringFromKey(sender)+
                StringUtil.getStringFromKey(recipient) +
                Float.toString(value) + sender);
    }

    public void generateSignature(PrivateKey privateKey){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        signature = StringUtil.getECDSASig(privateKey, data);
    }

    public boolean verifySignature(){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
    }
}
