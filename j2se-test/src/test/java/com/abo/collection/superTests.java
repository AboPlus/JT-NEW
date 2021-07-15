package com.abo.collection;

import org.junit.Test;

/**
 * 当创建子类对象时，一定会触发子类的构造方法
 * 子类的构造方法第一行隐藏着一行super()，用来执行父类的无参构造！！！
 */
public class superTests extends Fu{
    String name = "son";
    @Test
    public void testSuper(){
        /*String name = super.name;
        System.out.println(name);
        new Fu(100);*/
        //System.out.println(Fu.name);
    }
}
class Fu{
    static String name = "father";
    public Fu(){}
    public Fu(Integer age){
        System.out.println(age);
    }

}