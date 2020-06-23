package com.example.demo.provider;

import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.Wallet;
import com.example.demo.repository.WalletRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class WalletProvider {
    @Autowired private WalletRepository walletRepository;

    public void addWallet(){
        findAllWallets().add(new Wallet());
    }

    public ArrayList<Wallet> findAllWallets(){
        return walletRepository.findAllWallets();
    }

    public String test(){
        if(findAllWallets().size()>1){
            Transaction transaction = new Transaction(findAllWallets().get(0).getPublicKey(), findAllWallets().get(1).getPublicKey(), 5, null);
            transaction.generateSignature(findAllWallets().get(0).getPrivateKey());
            return String.valueOf(transaction.verifySignature());
        }
        return "error";
    }
}
