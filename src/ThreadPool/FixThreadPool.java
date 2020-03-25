package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 6; i++) {
            service.submit(()->{//service.execute   submit有返回值，若返回值没有抛出异常
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        service.shutdown();
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(6);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
        System.out.println(service);

    }
}
