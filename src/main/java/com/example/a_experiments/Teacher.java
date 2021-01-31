package com.example.a_experiments;

public class Teacher {
    private String name;
    private String gender;
    private int age;

    public Teacher(String name) {
        this(name, "男");
        System.out.println("第一行构造器");
    }

    public Teacher(String name, String gender) {
        this(name, gender, 45);
        System.out.println("第二行构造器");
    }

    public Teacher(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        System.out.println("这是第三个构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 100) {
            return;
        }
        this.age = age;
    }

    public void lecture() {

    }

    public void doExec() {

    }

    public String say() {
        String str = "我是" + name + ",性别  " + gender + ",年龄" + age;
        return str;
    }


}