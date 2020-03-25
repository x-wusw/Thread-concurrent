package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 无限线程池，具有自动线程回收 单个后台线程 任务按照顺序执行
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 2; i++) {
            final int j = i;
            service.execute(()->{
                System.out.println(j +""+Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        service.shutdown();
        System.out.println(service);
    }


}
