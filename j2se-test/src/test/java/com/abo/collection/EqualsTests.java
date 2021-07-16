package com.abo.collection;

import org.junit.Test;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class EqualsTests {

    @Test
    public void testEqueals(){
        Teacher t = new Teacher();
        t.speak();
    }
}

class Teacher{
    static int age=30;
    String name="王康";
    //非静态测试
    public void speak(){
        System.out.println(age);//非静态能调用静态变量
        System.out.println(name);//非静态能调用非静态变量
        qiao();//非静态能调用静态方法
    }
    //静态测试
    public static void qiao(){
        System.out.println(age);//静态能调用静态
        //System.out.println(name);//静态不能调用非静态变量，只能调用静态变量
        //speak();//静态不能调用非静态方法，只能调用静态方法
    }
}
