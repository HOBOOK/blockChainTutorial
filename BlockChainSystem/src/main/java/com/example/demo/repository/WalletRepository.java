package com.example.demo.repository;

import com.example.demo.entity.Wallet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class WalletRepository {
    private static ArrayList<Wallet> wallets = new ArrayList<>();

    public ArrayList<Wallet> findAllWallets(){
        return wallets;
    }
}
