package com.example.HRM_practice.util;

import java.util.regex.Pattern;

public class ValidateUtil {

    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]{5,15}$";
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{5,30}$";


    public static boolean isUserNameCorrect(String userName){
        return userName != null && Pattern.compile(USERNAME_REGEX).matcher(userName).matches();
    }

    public static boolean isPasswordCorrect(String password){
        return password != null && Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }
}