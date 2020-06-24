package com.example.demo.common;

import com.example.demo.entity.transaction.Transaction;

import java.security.*;
import java.util.ArrayList;
import java.util.Base64;

public class StringUtil {
    public static String getSha256(String str) {
        String SHA;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte data : byteData) {
                sb.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }

    public static byte[] getECDSASig(PrivateKey privateKey, String input){
        Signature dsa;
        byte[] output = new byte[0];
        try{
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return output;
    }

    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature){
        try{
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }


    public static String getMerkleRoot(ArrayList<Transaction> transactions){
        int count = transactions.size();
        ArrayList<String> previousTreeLayer = new ArrayList<>();
        for(Transaction transaction : transactions){
            previousTreeLayer.add(transaction.getTransactionId());
        }

        ArrayList<String> treeLayer = previousTreeLayer;
        while(count > 1){
            treeLayer = new ArrayList<>();
            for(int i = 1; i < previousTreeLayer.size(); i++){
                treeLayer.add(getSha256(previousTreeLayer.get(i-1) + previousTreeLayer.get(i)));
            }
            count = treeLayer.size();
            previousTreeLayer = treeLayer;
        }
        String merkleRoot = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
        return merkleRoot;
    }

}
