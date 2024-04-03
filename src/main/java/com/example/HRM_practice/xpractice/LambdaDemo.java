package com.example.HRM_practice.xpractice;

import java.util.Arrays;

public class LambdaDemo {
    public static void main(String[] args){
        String[] names = {"Justin", "caterpillar", "Bush"};
        Arrays.sort(names, (name1, name2) -> name1.length() - name2.length());
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, StringOrder::byLength);
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, String::compareTo);
        System.out.println(Arrays.toString(names)); //將排序後的結果以字串形式輸出，單純印names 印出的會是他的位址

        Arrays.sort(names, StringOrder::byLexicographyIgnoreCase);
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, String::indexOf);
        System.out.println(Arrays.toString(names));

    }
}
