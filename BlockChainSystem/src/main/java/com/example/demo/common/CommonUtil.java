package com.example.demo.common;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public void log(String...log){
        System.out.println("######### Log ##########");
        for(String l : log){
            System.out.println(l);
        }
        System.out.println();
    }
}
