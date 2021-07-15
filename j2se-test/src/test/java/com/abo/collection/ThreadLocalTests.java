package com.abo.collection;

import org.junit.Test;

public class ThreadLocalTests {

    private final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            //return super.initialValue();
            return 0;
        }
    };

    @Test
    public void startThread(){
        for (int i =0;i<3;i++){
            Thread t = new Thread() {
                @Override
                public void run() {
                    addByThreadLocal();
                }
            };
            t.start();
        }
    }

    private void addByThreadLocal(){
        Integer num = 0;
        for (int i = 0 ; i<5;i++){
            /*Integer num = THREAD_LOCAL_NUM.get();
            num+=1;
            THREAD_LOCAL_NUM.set(num);
            String name = Thread.currentThread().getName();
            System.out.println(name + " : ThreadLocal num = " + num);*/
            num+=1;
            String name = Thread.currentThread().getName();
            System.out.println(name + " : Thread num = " + num);
        }
    }


}
