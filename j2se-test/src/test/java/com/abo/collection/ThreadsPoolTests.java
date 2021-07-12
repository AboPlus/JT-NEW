package com.abo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

public class ThreadsPoolTests {

    @Test
    public void testThreadsPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i =0 ;i<5; i++) {
            final int index = i;
            executorService.execute(()-> System.out.println(Thread.currentThread().getName()+"-"+index));
        }
        executorService.shutdown();
    }

    @Test
    public void testThreadsPool02(){
        List list = new ArrayList();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0 ; i<10; i++) {
            final int index = i;
            Future<?> submit = executorService.submit(() -> {
                System.out.println(index + "this is run");
            });
            list.add(submit);
        }
        for (Object list1 :list){
            System.out.println("this is result " + list1);
        }



    }




}
