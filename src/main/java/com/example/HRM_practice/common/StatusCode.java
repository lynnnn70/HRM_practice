package com.example.HRM_practice.common;

public enum StatusCode {

    //enum列舉包含一系列可能的狀態碼
    OK(1),
    InvalidData(-1), //輸入資料不一致，更改無效
    InternalError(-2),
    AccountUnavailable(-3),
    InvalidToken(-4),
    NotFound(-5), //錯誤的舊密碼 找不到
    Duplicate(-6), //新跟舊密碼一樣
    WrongFormat(-7); //輸入格式錯誤





    private final int value;

    StatusCode(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
