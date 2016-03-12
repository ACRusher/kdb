package jit;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouxiliang on 2015/11/9.
 */
public class Calculator {

    private AtomicInteger cnt=new AtomicInteger(0);

    private long getTime(){
        return System.currentTimeMillis();
    }

    private String md5(String origin){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            messageDigest.update(origin.getBytes("utf-8"));
            byte[] arr=messageDigest.digest();
            if(arr!=null){
                return toHex(arr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toHex(byte[] arr){
        StringBuilder sb=new StringBuilder();
        for(byte b : arr){
            String hex=Integer.toHexString(0x00ff&b).toUpperCase();
            if(hex.length()==1) sb.append("0");
            sb.append(hex);
        }
        return sb.toString();
    }


    public String getUUID(){
        StringBuilder sb=new StringBuilder();
        return md5(sb.append(getIp()).append(getTime()).append(cnt.getAndIncrement()).toString());
    }

    public String getIp(){
        try {
            byte[] ip=InetAddress.getLocalHost().getAddress();
            StringBuilder sb=new StringBuilder();
            for(byte b : ip){
                sb.append((0x0ff&b)).append(".");
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger();
        Calculator calculator=new Calculator();
        long cost=0;
        for(int i=0;i<100000;++i){
            long start= System.nanoTime();
            String t=calculator.getUUID();
            long end=System.nanoTime();
            atomicInteger.incrementAndGet();
            if(Math.abs(end-start-cost)>10000){
                System.out.println(atomicInteger.get()+" >> "+(end-start));
            }
            cost=end-start;
        }
    }
}
