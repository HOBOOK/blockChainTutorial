package com.example.demo.controller;

import com.example.demo.entity.Wallet;
import com.example.demo.provider.WalletProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletProvider walletProvider;

    @GetMapping
    public Collection<Wallet> findAllWallets(){
        return walletProvider.findAllWallets();
    }

    @GetMapping(value = "/test")
    public String getTestResult(){
        return walletProvider.test();
    }

    @PostMapping
    public void mineBlock(){
        walletProvider.addWallet();
    }
}
