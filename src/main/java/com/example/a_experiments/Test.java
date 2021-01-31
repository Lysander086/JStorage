package com.example.a_experiments;

public class Test {

    public static void testArgs(String ...paramMap){
        System.out.println(paramMap.length);
        for (String param: paramMap) {
            System.out.println(param);
        }
    }

    public static void main(String[] args) {
        testArgs("test", "hello", "ff");
    }

}
