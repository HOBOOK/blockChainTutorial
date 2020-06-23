package com.example.demo.entity;

import lombok.Getter;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    @Getter
    private PrivateKey privateKey;

    @Getter
    private PublicKey publicKey;

    public Wallet(){
        generateKeyPair();
    }

    private void generateKeyPair(){
        try{
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("prime192v1");

            generator.initialize(ecGenParameterSpec, random);
            KeyPair keyPair = generator.generateKeyPair();

            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}