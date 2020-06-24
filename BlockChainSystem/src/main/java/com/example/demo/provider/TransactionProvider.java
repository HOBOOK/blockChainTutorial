package com.example.demo.provider;

import com.example.demo.common.CommonUtil;
import com.example.demo.common.StringUtil;
import com.example.demo.entity.Wallet;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.transaction.TransactionInput;
import com.example.demo.entity.transaction.TransactionOutput;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.ArrayList;

@Component
public class TransactionProvider {
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private WalletRepository walletRepository;
    @Autowired private CommonUtil commonUtil;

    private static int sequence = 0;
    private static float minimumTransaction = 0.1f;

    public ArrayList<Transaction> findAllTransactions(){
        return transactionRepository.findAllTransactions();
    }

    public void vote(Wallet wallet, ArrayList<TransactionInput> transactionInput){
        Transaction transaction;
        if(walletRepository.findAllWallets().size()>1){
            transaction = new Transaction(wallet.getPublicKey(), walletRepository.findAllWallets().get(0).getPublicKey(), 1,transactionInput);
        }else{
            transaction = new Transaction(wallet.getPublicKey(), wallet.getPublicKey(), 1,transactionInput);
        }
        findAllTransactions().add(transaction);
        commonUtil.log("투표성공");
    }

    public boolean processTransaction(Transaction transaction){
        if(!verifySignature(transaction)){
            commonUtil.log("#Transaction Signature failed to verify");
            return false;
        }

        for(TransactionInput i : transaction.getInputs()){
            i.setUTXO(transactionRepository.findUTXOs().get(i.getTransactionOutputId()));
        }

        if(getInputsValue(transaction) < minimumTransaction){
            commonUtil.log("Transaction Inputs too small : " + getInputsValue(transaction),
                    "Please enter the amount greater than " + minimumTransaction);
            return false;
        }

        float leftOver = getInputsValue(transaction) - transaction.getValue();
        transaction.setTransactionId(makeHashBlock(transaction));
        transaction.getOutputs().add(new TransactionOutput(transaction.getRecipient(), transaction.getValue(), transaction.getTransactionId()));
        transaction.getOutputs().add(new TransactionOutput(transaction.getSender(), leftOver, transaction.getTransactionId()));

        for(TransactionOutput o : transaction.getOutputs()){
            transactionRepository.findUTXOs().put(o.getId(), o);
        }

        for(TransactionInput i : transaction.getInputs()){
            if(i.getUTXO() == null) continue;
            transactionRepository.findUTXOs().remove(i.getUTXO().getId());
        }
        return true;
    }

    private float getInputsValue(Transaction transaction){
        float total = 0;
        for(TransactionInput i : transaction.getInputs()){
            if(i.getUTXO() == null) continue;
            total += i.getUTXO().getValue();
        }
        return total;
    }

    private float getOutputsValue(Transaction transaction){
        float total = 0;
        for(TransactionOutput o : transaction.getOutputs()){
            total += o.getValue();
        }
        return total;
    }

    public String makeHashBlock(Transaction transaction){
        sequence++;
        return StringUtil.getSha256(
                StringUtil.getStringFromKey(transaction.getSender())+
                        StringUtil.getStringFromKey(transaction.getRecipient()) +
                        Float.toString(transaction.getValue()) + transaction.getSender());
    }

    public void generateSignature(Transaction transaction, PrivateKey privateKey){
        String data = StringUtil.getStringFromKey(transaction.getSender()) + StringUtil.getStringFromKey(transaction.getRecipient()) + Float.toString(transaction.getValue());
        transaction.setSignature(StringUtil.getECDSASig(privateKey, data));
    }

    public boolean verifySignature(Transaction transaction){
        String data = StringUtil.getStringFromKey(transaction.getSender()) + StringUtil.getStringFromKey(transaction.getRecipient()) + Float.toString(transaction.getValue());
        return StringUtil.verifyECDSASig(transaction.getSender(), data, transaction.getSignature());
    }
}
