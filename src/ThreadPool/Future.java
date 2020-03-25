package ThreadPool;

import java.util.concurrent.*;

public class Future {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return 100;
        });

        new Thread(task).start();
        try {
            System.out.println(task.get());//没得到之前阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService service = Executors.newFixedThreadPool(1);
        java.util.concurrent.Future<Integer> f = service.submit(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return 1;
        });

        try {
            System.out.println(f.get());
            System.out.println(f.isCancelled());
            System.out.println(f.isDone());//是否执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
