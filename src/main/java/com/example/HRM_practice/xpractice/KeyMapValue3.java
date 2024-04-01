package com.example.HRM_practice.xpractice;

import lombok.var;

import java.util.TreeMap;

public class KeyMapValue3 {
    public static void main(String[] args){
        var map = new TreeMap<String, String>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");

        map.forEach((key, value) -> System.out.printf(" (Key %s, Value %s)%n ", key, value));

    }
}
