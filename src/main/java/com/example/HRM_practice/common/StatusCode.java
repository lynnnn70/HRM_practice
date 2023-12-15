package com.example.HRM_practice.common;

public enum StatusCode {

    //enum列舉包含一系列可能的狀態碼
    OK(1),
    InvalidData(-1),
    InternalError(-2),
    AccountUnavailable(-3),
    InvalidToken(-4),
    NotFound(-5),
    Duplicate(-6);

    private final int value;

    StatusCode(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
