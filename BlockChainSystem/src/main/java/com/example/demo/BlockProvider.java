package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BlockProvider {
    @Autowired
    private BlockRepository blockRepository;

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
}
