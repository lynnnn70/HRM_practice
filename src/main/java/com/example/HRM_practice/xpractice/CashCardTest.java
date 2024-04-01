package com.example.HRM_practice.xpractice;

public class CashCardTest {
    public static void main(String[] args){
        CashCard cashCard1 = new CashCard("1", 100, 0);
        cashCard1.store(500);
        System.out.println(cashCard1.balance);

        cashCard1.store(1000);
        System.out.println(cashCard1.balance);
        System.out.println(cashCard1.bonus);

        cashCard1.charge(50);
        System.out.println(cashCard1.balance);
        System.out.println(cashCard1.bonus);
    }

}
