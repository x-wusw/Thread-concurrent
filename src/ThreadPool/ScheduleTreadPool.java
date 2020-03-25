package ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * schedule方法创建具有各种延迟的任务，并返回可用于取消或检查执行的任务对象。
 * scheduleAtFixedRate和scheduleWithFixedDelay方法创建并执行定期运行的任务，直到取消。
 * 定时执行
 */
public class ScheduleTreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 5; i++) {
            service.scheduleAtFixedRate(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            },0,1000,TimeUnit.MILLISECONDS);
        }
    }
}
