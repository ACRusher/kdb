package com.kdb.logLock;

import com.kdb.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 控制台日志真的会成为系统瓶颈哦
 * 高并发的系统，配置日志时候一定要慎重再慎重
 *
 *  一些好的实践：
 *  1. 使用logback
 *  2. 尽量减少不必要的日志，日志信息中尽量不要打印 %C,M ,L
 *  3. additivity 要为 false 不要重复打印日志
 *  4. 如果没有实时打印日志的要求，最好使用AsyncAppender
 *  5. 每个日志配置自己的 Appender ，不要默认走到ROOT中的配置
 *
 * Created by 懿斐 on 2016/1/29.
 */
public class LogLockTests extends BaseTest {

    private String metaMessage="abcd12345!@#$";
    private String logMessage=null;

    public void init(){
        super.init();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<100;++i){
            sb.append(metaMessage);
        }
        logMessage=sb.toString();
    }

    @Test
    public void testLock(){
        final Logger logger = LoggerFactory.getLogger("console");
        logger.info(">>>>>>>>>>>> test console log on system.out");
        int n=200;
        final CountDownLatch start=new CountDownLatch(1);
        final CountDownLatch end=new CountDownLatch(n);
        final String msg=logMessage;
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        for(int i=0;i<n;++i){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        start.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long s=System.currentTimeMillis();
                    logger.info(msg);
                    logger.info(msg);
                    try {
                        Thread.currentThread().sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info(msg);
                    logger.info(msg);
                    end.countDown();
                    if(System.currentTimeMillis()-s>500) atomicInteger.incrementAndGet();
                }
            });
            thread.setName("TestThread-"+i);
            thread.start();
        }
        long s=System.currentTimeMillis();
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long e=System.currentTimeMillis();

        System.out.println(">>> 共耗时 :"+(e-s));
        System.out.println("rt>1s : "+atomicInteger.get());

    }

    @Test
    public void testLockCyclic(){
        for(int i=0;i<300;++i){
            testLock();
        }
    }



}
