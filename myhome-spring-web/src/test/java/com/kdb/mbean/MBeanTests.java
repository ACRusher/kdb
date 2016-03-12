package com.kdb.mbean;

import com.kdb.BaseTest;
import org.junit.Test;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 懿斐 on 2016/1/30.
 */
public class MBeanTests {//extends BaseTest {

    @Test
    public void testMonitorJVM() throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        //获取jvm堆相关的信息
        // jvm 默认 newRatio = 2 survivorRatio=1 , Xmn 优先级高于 newRatio
        // Heap Memory 不包括 Perm 区 ，maxMemory 要减去 1个survivorMemory
        ObjectName memory = new ObjectName("java.lang:type=Memory");
        CompositeData heapMemoryUsage = (CompositeData) mBeanServer.getAttribute(memory, "HeapMemoryUsage");
        Long initBytes = (Long) heapMemoryUsage.get("init");
        Long maxBytes = (Long) heapMemoryUsage.get("max");
        Long commtedBytes = (Long) heapMemoryUsage.get("committed");
        Long usedBytes = (Long) heapMemoryUsage.get("used");
        System.out.println("JVM Heap Memory (m) : init=" + initBytes / 1024/1024 + " max=" + maxBytes / 1024/1024 + " commited=" + commtedBytes / 1024/1024
                + " used="+usedBytes/1024/1024);
        CompositeData nonHeapMemoryUsage = (CompositeData) mBeanServer.getAttribute(memory, "NonHeapMemoryUsage");
        Long initBytes1 = (Long) nonHeapMemoryUsage.get("init");
        Long maxBytes1 = (Long) nonHeapMemoryUsage.get("max");
        Long commtedBytes1 = (Long) nonHeapMemoryUsage.get("committed");
        Long usedBytes1 = (Long) nonHeapMemoryUsage.get("used");
        System.out.println("JVM NonHeap Memory (m) : init=" + initBytes1 / 1024/1024 + " max=" + maxBytes1 / 1024/1024 + " commited=" + commtedBytes1 / 1024/1024
                + " used1="+usedBytes/1024/1024);

        createBlockThread();
        createWaitingThread();
        //获取jvm线程相关的信息
        ObjectName threading = new ObjectName("java.lang:type=Threading");
        Integer threadCount = (Integer) mBeanServer.getAttribute(threading, "ThreadCount");
        Integer daemonThreadCount = (Integer) mBeanServer.getAttribute(threading, "DaemonThreadCount");
        Long totalStartedThreadCount = (Long) mBeanServer.getAttribute(threading, "TotalStartedThreadCount");
        Integer peakThreadCount = (Integer) mBeanServer.getAttribute(threading, "PeakThreadCount");
        System.out.println("JVM thread statistics : threadCount="+threadCount+" daemonThreadCount="+daemonThreadCount
                +" totalStartedThreadCount="+totalStartedThreadCount+" peakThreadCount="+peakThreadCount +" blockedCount="+getBLockedThreadCount());

        ObjectName os = new ObjectName("java.lang:type=OperatingSystem");
        Long processCpuTime=(Long) mBeanServer.getAttribute(os,"ProcessCpuTime");
        System.out.println("JVM processCpuTime= "+(processCpuTime/(1000000*1000)));
        System.out.println("system time : "+System.currentTimeMillis());
        List list=new ArrayList();
        for(int i=0;i<2;++i){
            byte[] tmp=new byte[1024*40000];
            list.add(tmp);
        }

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ObjectName garbage = new ObjectName("java.lang:type=GarbageCollector,name=ParNew");
        Long collectionCount=(Long) mBeanServer.getAttribute(garbage,"CollectionCount");
        Long collectionTime=(Long) mBeanServer.getAttribute(garbage,"CollectionTime");
        System.out.println("JVM ParNew collectionCount="+collectionCount+" collectionTime="+collectionTime);

        ObjectName cmsGarbage = new ObjectName("java.lang:type=GarbageCollector,name=ConcurrentMarkSweep");
        Long cmsCollectionCount=(Long) mBeanServer.getAttribute(cmsGarbage,"CollectionCount");
        Long cmsCollectionTime=(Long) mBeanServer.getAttribute(cmsGarbage,"CollectionTime");
        System.out.println("JVM CMS cmsCollectionCount="+cmsCollectionCount+" cmsCollectionTime="+cmsCollectionTime);

        assert memory != null;
    }

    private Integer getBLockedThreadCount(){
        int result=0;
        int waiting=0;
        Map<Thread,?> map=Thread.getAllStackTraces();
        for(Map.Entry<Thread,?> entry : map.entrySet()){
            if(entry.getKey().getState().equals(Thread.State.BLOCKED)){
                result++;
            }
            if(entry.getKey().getState().equals(Thread.State.WAITING)){
                waiting++;
            }
        }
        System.out.println(">>>>> waiting "+waiting);
        return result;
    }

    private final Object lock=new Object();
    private final ReentrantLock reentrantLock=new ReentrantLock();
    private void createBlockThread(){
        //获取锁
        acquireObjectLock();
        //再次获取 ，线程将阻塞，状态为BLOCKED
        acquireObjectLock();
    }
    private void createWaitingThread(){
        //获取锁
        acquireReentrantLock();
        //再次获取 ，线程将阻塞，状态为WAITING
        acquireReentrantLock();
    }

    private void acquireReentrantLock(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally {
                    reentrantLock.unlock();
                }
            }
        }).start();
    }

    private void acquireObjectLock(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (true){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
