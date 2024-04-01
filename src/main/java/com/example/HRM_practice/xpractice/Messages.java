package com.example.HRM_practice.xpractice;

import lombok.var;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.out;

public class Messages {
    public static void main(String[] args){
        var messages = new HashMap<String, String>();
        messages.put("Ian", "Hi, how are you");
        messages.put("Joan", "Hi, how's going");
        messages.put("Max", "hey, what's up");

//        var console = new Scanner(System.in);
//        out.print("取得誰的訊息: ");

//        String message = messages.get(console.nextLine()); //若要指定鍵 取回對應的值 用get
//        out.println(message);
//        out.print(messages);

//        var messages2 = new TreeMap<String, String>( (s1, s2) ->s1.compareTo(s2)); //實際上定義了一個Comparator 用於比較兩個String類型的物件s1 s2(依照字母順序進行比較)
        var messages2 = new TreeMap<String, String>(Comparator.reverseOrder());
        messages2.put("Max", "hey, what's up");
        messages2.put("Ian", "Hi, how are you");
        messages2.put("Joan", "Hi, how's going");

        out.println(messages2);



    }
}
