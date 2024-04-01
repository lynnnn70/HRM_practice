package com.example.HRM_practice.xpractice;

import lombok.var;

import java.util.HashMap;
import java.util.Map;

public class KeyMapValue {
    public static void main(String[] args){
        var map = new HashMap<String, String>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");

        System.out.println("顯示鍵Key");
        //Ketset() 傳回Set
        map.keySet().forEach(System.out::println);

        System.out.println("顯示值Value");
        //values() 傳回Collection
        map.values().forEach(System.out::println);

        System.out.println("同時顯示Key, value");
        //entrySet() 傳回Set物件
        map.entrySet().forEach(System.out::println);

    }

    static void foreach(Iterable<Map.Entry<String, String>> iterable){
        for(Map.Entry<String, String> entry : iterable){
            System.out.printf("Key %s", "Value %s", entry.getKey(), entry.getValue());
        }

    }
}
