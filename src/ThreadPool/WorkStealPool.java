package ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个维护足够的线程以支持给定的并行级别的线程池，并且可以使用多个队列来减少争用。
 */
public class WorkStealPool {
    public static void main(String[] args) throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors());//获取处理器 四核
        ExecutorService service = Executors.newWorkStealingPool(4);
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(1000));
        System.out.println(service);
        //service.shutdown();
        System.in.read();//守护线程，主线程不阻塞，显示不出来
        System.out.println(service);
    }


    static class R implements Runnable{
        int time;
        R(int t){
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}