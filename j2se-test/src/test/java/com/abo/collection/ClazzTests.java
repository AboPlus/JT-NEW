package com.abo.collection;

import org.junit.Test;

public class ClazzTests {
    @Test
    public void testReflection() throws ClassNotFoundException {
        method();
    }

    public static void method() throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        System.out.println(stringClass);
        Class<? extends String> aClass = new String().getClass();
        System.out.println(aClass);
        Class<?> aaa = Class.forName("java.lang.String");
        System.out.println(aaa);
    }


}
