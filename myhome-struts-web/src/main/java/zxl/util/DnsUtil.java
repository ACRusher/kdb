package zxl.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by zhouxiliang on 2015/12/23.
 */
public class DnsUtil {

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        System.out.println(System.getProperty( "java.version"));
        for(int i=0;;++i){
            try {
                Thread.currentThread().sleep(1000);
                InetAddress[] arr=InetAddress.getAllByName("www.qwert.com");
                System.out.println(Arrays.toString(arr));
            }catch (Exception e){
                e.printStackTrace(System.out);
            }
        }
    }
}
