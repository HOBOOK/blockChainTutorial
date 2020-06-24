package com.example.demo.provider;

import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.Wallet;
import com.example.demo.repository.WalletRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Security;
import java.util.ArrayList;

@Component
public class WalletProvider {
    @Autowired
    private WalletRepository walletRepository;

    WalletProvider() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public void addWallet() {
        findAllWallets().add(new Wallet());
    }

    public ArrayList<Wallet> findAllWallets() {
        return walletRepository.findAllWallets();
    }
}