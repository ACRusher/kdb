package com.kdb.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

/**
 * Created by 懿斐 on 2016/1/30.
 */
public class LockTests {

    @Test
    public void testLock(){
        /**
         * 任意对象可以当一个锁，
         * 他只有一个condition，即是他自己
         * 任何condition操作必须首先获得锁
         */
       final  Object object=new Object();
        synchronized (object) {
            try {
                Executors.newFixedThreadPool(1).submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (object) {
                            object.notifyAll();
                        }
                    }
                });
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("success");
        }

    }

    //模拟一个 由于重入导致的死锁
    @Test
    public void testDeadLockOfReentrant(){
        Object lock=new Object();
        synchronized (lock){
            System.out.println("first get lock.");
            synchronized (lock){
                System.out.println("second get success");
            }
        }
        System.out.println(" hit this line.");


    }
    @Test
    public void testReentrantLock() throws InterruptedException {
        ReentrantLock reentrantLock=new ReentrantLock(true);
        reentrantLock.lock();//普通枷锁
        reentrantLock.lockInterruptibly();//在获取锁的过程中可以相应中断
        reentrantLock.isLocked();//不阻塞
        reentrantLock.tryLock();//不阻塞
        reentrantLock.tryLock(1, TimeUnit.MINUTES);//设置等待的最大实践
        Condition condition=reentrantLock.newCondition();
        condition.signal();
        condition.signalAll();
        condition.await();//这三个必须成对使用，且必须在锁的范围内 ， await 和 wait一样放弃锁的拥有并进入等待事件队列

    }

    /**
     * 读写锁：
     * 1. 读锁 是独占所
     * 2. 写锁之间可以共享
     * 3. 读锁比写锁的优先级高，可以理解为 读锁和写锁在一个优先级队列中等待获取锁
     */
    @Test
    public void testReadWriteLock(){
        ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

        final ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();
        final List<Integer> list=new ArrayList<Integer>();

        int readCnt=5;
        final int readInterval=1000;
        for(int i=0;i<readCnt;++i){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        readLock.lock();
                        try{
                            System.out.println(Thread.currentThread().getName()+" read success. data: "+list);
                            try {
                                Thread.currentThread().sleep(readInterval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }finally {
                            readLock.unlock();
                        }
                    }
                }
            }, "ReadThread-" + i).start();
        }

        final AtomicInteger atomicInteger=new AtomicInteger(0);
        final int writeCnt=1;
        for(int i=0;i<writeCnt;++i){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        writeLock.lock();
                        try{
                            list.clear();
                            list.add(atomicInteger.incrementAndGet());
                            System.out.println(Thread.currentThread().getName() + " write success. data: " + list);
                            try {
                                Thread.currentThread().sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }finally {
                            writeLock.unlock();
                        }
                        System.out.println("write end.>>");
                        try {
                            Thread.currentThread().sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }, "WriteThread-" + i).start();
        }


        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
