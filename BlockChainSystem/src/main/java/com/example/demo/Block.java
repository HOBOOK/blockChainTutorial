package com.example.demo;

import lombok.Getter;

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

    @Getter
    private String data;

    private String target = "00000";

    private int nonce;

    private int targetDepth = 5;

    private long timeStamp;

    public Block(String data, String prevHash){
        this.data = makeHashData(data);
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        mineNewBlock();
    }

    private void mineNewBlock(){
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
