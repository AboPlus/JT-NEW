package com.abo.collection;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class testThread{

    @Test
    public void testThread() throws InterruptedException {
        Thread thread = new Thread();
        //获取线程的标志
        long id = thread.getId();
        System.out.println("the thread's id is "+id);
        thread.setName("Abo's Thread");
        String name = thread.getName();
        System.out.println("the thread's name is "+ name);
        //执行任务
        thread.run();
        //启动线程
        thread.start();
        Thread.sleep(50);
        String currnetThreadName = Thread.currentThread().getName();
        System.out.println(currnetThreadName);//main
    }

    @Test
    public void testExtendsThread(){
        myThread thread = new myThread();
        thread.setName("Abo's Thread");
        String name = thread.getName();
        System.out.println("this thread name is "+name);
        //thread.run();
        thread.start();
    }

    @Test
    public void testImplementRunnable(){
        myRunnable target = new myRunnable();
        Thread thread = new Thread(target);
        thread.start();
    }

    @Test
    public void testImplementCallable() throws ExecutionException, InterruptedException {
        callableImpl callable = new callableImpl();
        FutureTask futureTask = new FutureTask(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        Object o = futureTask.get();
    }

    @Test
    public void testSellTickets(){
        MyTickets windows1 = new MyTickets();
        windows1.setName("窗口一");
        MyTickets windows2 = new MyTickets();
        windows1.setName("窗口二");
        MyTickets windows3 = new MyTickets();
        windows1.setName("窗口三");
        MyTickets windows4 = new MyTickets();
        windows1.setName("窗口四");

        windows1.start();
        windows2.start();
        windows3.start();
        windows4.start();


    }


}

class myThread extends Thread{
    @Override
    public void run() {
        System.out.println("the class has override the method of run where thread's name is myThiread");
    }
}

class myRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("the class has override the method of run where thread's name is myRunnable");
    }
}

class callableImpl implements Callable{
    @Override
    public Object call() throws Exception {
        return "this class implement Callable";
    }
}

class MyTickets extends Thread{
    private static AtomicInteger tickets = new AtomicInteger(100);
    @Override
    public void run() {
        while (true) {
            synchronized (MyTickets.class) {
                if ( tickets.get() > 0 ) {
                    int hasTicket = tickets.getAndDecrement();
                    System.out.println(super.getName()+"剩余票数"+hasTicket);
                } else {
                    break;
                }
            }
        }
    }
}