package cron.expression;

import java.util.Date;

/**
 * Created by zhouxiliang on 2015/11/18.
 */
public class MySpeakJob {
    public void run(){
        System.out.println("hello , the time is : "+new Date());
    }
}
