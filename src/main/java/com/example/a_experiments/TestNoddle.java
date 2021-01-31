package com.example.a_experiments;

/*
 *      构造函数，在对象初始化的时候进行赋值操作
特点： 1.名称必须和类名完全一致
        2.不写返回值
        3.当开发人员没有写任何构造函数是，jvm会自动生成一个没有参数的构造函数
        4.当开发人员手动写了任意一个构造函数后，Jvm就不会在生成不在参数的构造函数了
 * */
public class TestNoddle {
    public static void main(String[] args) {

//创建完对象，就会有一个this引用指向这个对象
//每一个对面内部都有一个this引用指向这个对象
        Noddle no = new Noddle("小碗", true);
        no.make();
    }
}

//函数名一致，但参数不一致，称为函数的重载;
class Noddle {
    String bowlType;
    boolean moreMeat;

    public Noddle(String bowlType, boolean moreMeat) {
//      bowlType = "小碗";
//      moreMeat = true;
        this.bowlType = bowlType;
        this.moreMeat = moreMeat;

    }

    public void make() {
        String s = "不加肉";
        if (moreMeat) {
            s = "加肉";
        }
        System.out.println("一碗热腾腾的" + bowlType + s + "小盘鸡盘面煮好了");
    }
}