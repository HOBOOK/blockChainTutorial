package com.example.demo.provider;

import com.example.demo.common.CommonUtil;
import com.example.demo.entity.Block;
import com.example.demo.entity.Wallet;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BlockProvider {
    @Autowired
    private BlockRepository blockRepository;

    @Autowired private TransactionProvider transactionProvider;

    @Autowired private CommonUtil commonUtil;

    public ArrayList<Block> findAllBlockChain(){
        return blockRepository.findAllBlockChain();
    }

    public void mineBlock(String data){
        String prevHash = findAllBlockChain().isEmpty() ? "0" : findAllBlockChain().get(findAllBlockChain().size()-1).getHash();
        findAllBlockChain().add(new Block(data, prevHash));
    }

    public Boolean isChainValid(){
        Block currentBlock;
        Block prevBlock;

        ArrayList<Block> blockChain = findAllBlockChain();

        for(int i = 1; i < blockChain.size(); i++){
            currentBlock = blockChain.get(i);
            prevBlock = blockChain.get(i-1);

            if(!currentBlock.getHash().equals(currentBlock.makeHashBlock())){
                return false;
            }
            if(!prevBlock.getHash().equals(currentBlock.getPrevHash())){
                return false;
            }
        }
        return true;
    }

    public void addTransaction(Block block, Transaction transaction){
        if(transaction == null){
            return;
        }
        if(block.getPrevHash() != "0"){
            if(!transactionProvider.processTransaction(transaction)){
                commonUtil.log("Transaction failed to process. Discarded.");
                return;
            }
        }
        transactionProvider.findAllTransactions().add(transaction);
        commonUtil.log("Transaction Successfully added to Block");
    }
}
