package ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 */
class CustomTask implements Runnable{
    private  int id;
    public CustomTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("#" + id + "   threadId=" + Thread.currentThread().getName() );
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}

public class ThreadPoolExecuter {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 60, TimeUnit.MICROSECONDS, queue);

        for (int i=0; i<7; i++){
            Runnable task = new CustomTask(i);
            pool.execute(task);
        }

        pool.shutdown();
    }

}