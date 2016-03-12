package com.kdb;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author xiliang.zxl
 * @date 2016-02-12 上午10:26
 */
public class JVMHook {

    /**
     * 在 kill -HUP 或 kill -TERM 信号下才会执行 jvm hook
     * kill -KILL 会强制停止进程，不会执行 hook
     *
     * @throws InterruptedException
     */
    @Test
    public void testHook() throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("executing jvm shutdown hook...");
            }
        },"testThread-1"));
        for(;;){
            System.out.println(new Date());
            Thread.currentThread().sleep(3000);
        }
    }

    /**
     * Array 、 Linked 在设定容量的时候都会限制最大等待队列
     *
     * LinkedBlockedQueue 不设容量可以无限入队列
     */
    @Test
    public void testBlockingQueue(){
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(3);
        for(int i=0;i<3;++i){
            arrayBlockingQueue.add(new Object());
        }
        LinkedBlockingDeque linkedBlockingDeque=new LinkedBlockingDeque();
        for(int i=0;i<1000;++i){
            linkedBlockingDeque.add(new Object());
        }
    }
}
