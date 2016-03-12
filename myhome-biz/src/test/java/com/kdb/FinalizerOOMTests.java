package com.kdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouxiliang on 2015/12/19.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class FinalizerOOMTests {

    /**
     * 实现 finalize 的对象，容易应发oom ，除非真的有必要，慎用finalize
     */
    @Test
    public void testOOM(){
        for(int i=0;i<3;++i){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    createObject();
                }
            });
            thread.setName("mythread-"+i);
            thread.setDaemon(false);
            thread.setPriority(10);
            thread.start();
        }
        try {
            CountDownLatch countDownLatch=new CountDownLatch(1);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createObject(){
        for(int i=0;;++i){
            BadClass badClass=new BadClass();
            System.out.println(Thread.currentThread().getName()+" >> "+BadClass.atomicInteger.get());
        }
    }


    private static class BadClass {
        private byte[] holder=new byte[1024*1024*10];// 10M

        public BadClass() {
            BadClass.atomicInteger.incrementAndGet();
        }

        private static AtomicInteger atomicInteger=new AtomicInteger(0);
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            BadClass.atomicInteger.decrementAndGet();
        }
    }
}
