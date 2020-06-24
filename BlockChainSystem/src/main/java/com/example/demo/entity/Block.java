package com.example.demo.entity;

import com.example.demo.common.StringUtil;
import com.example.demo.entity.transaction.Transaction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getData() {
        return data;
    }

    @Getter
    private String hash;

    @Getter
    private String prevHash;

    private String merkleRoot;

    private ArrayList<Transaction> transactions = new ArrayList<>();

    @Getter
    private String data;

    private String target = "00000";

    private int nonce;

    private int targetDepth = 5;

    @Getter
    private long timeStamp;

    public Block(String data, String prevHash){
        this.data = makeHashData(data);
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        mineNewBlock();
    }

    private void mineNewBlock(){
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        while(hash == null || !hash.substring(0,targetDepth).equals(target)){
            nonce++;
            hash = makeHashBlock();
        }
    }

    public String makeHashBlock(){
        return StringUtil.getSha256(prevHash +
                Long.toString(timeStamp)+
                data +
                Integer.toString(nonce));
    }

    public String makeHashData(String data){
        return StringUtil.getSha256(data);
    }
}
