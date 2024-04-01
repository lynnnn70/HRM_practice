package com.example.HRM_practice.xpractice;

import java.util.Objects;

public class Person {
    private String name;
    public int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, age);  //使用name, age來計算散列碼
    }

    @Override
    public boolean equals(Object obj){
        //檢查是否是同一個物件的參考
        if(this == obj){
            return true;
        }

        //檢查是否為null或類型不匹配
        if(obj == null || getClass() !=obj.getClass()){
            return false;
        }

        //將物件轉換為相同的類型
        Person person = (Person) obj;

        //檢查兩個物件的欄位是否相等
        return age == person.age && Objects.equals(name, person.name);

    }


}
