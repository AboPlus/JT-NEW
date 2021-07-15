package com.abo.collection;

import org.junit.Test;

public class SynThreadTests {

    @Test
    public void testSynThread(){
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(new SyncThread(o1, o2), "t1");
        Thread t2 = new Thread(new SyncThread(o1, o2), "t2");
        t1.start();
        t2.start();
    }
}

class SyncThread implements Runnable{
    private Object obj1;
    private Object obj2;
    public SyncThread(Object o1,Object o2){
        this.obj1 = o1;
        this.obj2 = o2;
    }
    @Override
    public void run() {
        synchronized (obj1){
            work();
            synchronized (obj2){
                work();
            }
        }
    }

    private void work(){
        try {
            Thread.sleep(30000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}