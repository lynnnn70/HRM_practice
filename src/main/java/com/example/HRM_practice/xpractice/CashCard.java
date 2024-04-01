package com.example.HRM_practice.xpractice;

public class CashCard {

    String number;
    int balance;
    int bonus;

    CashCard(String number, int balance, int bonus){
        this.number = number;
        this.balance = balance;
        this.bonus = bonus;
    }

    void store(int money){
        if(money > 0){
            this.balance += money;  // this.balance參照的是目前物件(即正在呼叫store方法的物件)
            if(money >= 1000){
                this.bonus ++;
            }
        }else {
            System.out.println("儲負的金額???");
        }
    }

    void charge(int cost){
        if(cost > 0){
            if(cost <= this.balance){
                this.balance -= cost;
            }else {
                System.out.println("餘額不足，請加值");
            }
        }else {
            System.out.println("花費是負數???");
        }
    }
}
