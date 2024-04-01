package com.example.HRM_practice.xpractice;

import java.util.HashMap;

public class PersonTest {

    public static void main(String[] args){
        Person person1 = new Person("Alice", 20);
        Person person2 = new Person("Alice", 20);
//        Person person2 = new Person("Jim", 22);

        //因 Person 類別 實現了 hashCode() 和 equals() 方法，根據 name 和 age 來確定兩個 Person 物件是否相等
        System.out.println(person1.hashCode() == person2.hashCode());
        System.out.println(person1.equals(person2));
        //因此，即使 person1 和 person2 是兩個不同的物件實例，但它們根據 hashCode() 和 equals() 被視為相等的，所以在將它們放入 HashMap 中時，只會有一個鍵被存儲。
        HashMap<Person, String> map = new HashMap<>();
        map.put(person1, "Value 1");
        map.put(person2, "Value 2");

        System.out.println(map.size());
    }


}
