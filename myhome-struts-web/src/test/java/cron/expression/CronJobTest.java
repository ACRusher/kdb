package cron.expression;

import base.BaseTest;
import org.junit.Test;
import org.quartz.CronExpression;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by zhouxiliang on 2015/11/18.
 */
public class CronJobTest extends BaseTest{

    @Test
    public void testCronJob()throws Exception{
        new CountDownLatch(1).await();
    }

    @Test
    public void testScheduledExecutorService(){
        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("scheduleAtFixedRate "+new Date());
                return null;
            }
        }),1,5,TimeUnit.SECONDS );
    }
    @Test
    public void testCronExpression()throws Exception{
        CronExpression cronExpression=new CronExpression("1/3 * * * * ?");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        while (true){
            Thread.sleep(2999);
            System.out.println(sdf.format(cronExpression.getNextValidTimeAfter(new Date())));
            System.out.println(sdf.format(cronExpression.getNextInvalidTimeAfter(new Date())));
        }
    }
}
