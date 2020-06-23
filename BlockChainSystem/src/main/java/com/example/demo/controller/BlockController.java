package com.example.demo.controller;

import com.example.demo.provider.BlockProvider;
import com.example.demo.entity.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/blocks")
public class BlockController {
    @Autowired
    private BlockProvider blockProvider;

    @GetMapping
    public Collection<Block> findAllBlockChain(){
        if(!blockProvider.isChainValid()){
            System.out.println("깨진 블록체인");
        }
        return blockProvider.findAllBlockChain();
    }

    @PostMapping
    public String mineBlock(@RequestParam(name="data") String data){
        blockProvider.mineBlock(data);
        return "OK";
    }
}
