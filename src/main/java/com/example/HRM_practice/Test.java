package com.example.HRM_practice;

import com.example.HRM_practice.model.entity.Roles;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        // 1
        List<String> numberList = new ArrayList<>();
        numberList.add("1");
        numberList.add("2");
        numberList.add("3");

        for (String n : numberList) {
            n = String.valueOf(n.equals("2"));
            break;
        }
        String string = numberList.stream()
                .map(n -> String.valueOf(n.equals("2")))
                .toString();


        //2 TODO 繼續
        List<Integer> idList = Arrays.asList(1, 2, 3);
        List<String> nameList = Arrays.asList("cat", "dog", "bear");
        List<Roles> rolesList = new ArrayList<>();
        Roles roles = null;
        for (int i = 0; i < idList.size(); i++) {
            roles = new Roles();
            roles.setRoleId(idList.get(i));
            roles.setRoleName(nameList.get(i));
            rolesList.add(roles);
        }

        List<Integer> ids = Arrays.asList(10, 20, 30);
//        List<TestObject> testObjectList = new ArrayList<>();
//        for (Integer id : ids) {
//            TestObject testObject = new TestObject();
//            testObject.setId(id);
//            testObject.setRolesList(rolesList);
//            testObjectList.add(testObject);
//        }

        List<TestObject> testObjectList = ids.stream()
                .map(id -> {
                    TestObject testObject = new TestObject();
                    testObject.setId(id);
                    testObject.setRolesList(rolesList);
                    return testObject;
                }).collect(Collectors.toList());


        System.out.println(testObjectList);


    }
}
