package concurrent;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *LinkedBlockingDeque  阻塞式同步容器
 */
public class Demo5 {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Random r = new Random();
        //开启两个生产者
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    try {
                        queue.put(j+"a");//如果满了则等待
                        TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                    }catch (InterruptedException e){
                        e.printStackTrace();

                    }
                }
            },"producer").start();
        }
        //开启5个消费者
        for (int i = 0; i < 5; i++) {
             new Thread(()->{
                 for (;;)
                 try {
                     System.out.println(Thread.currentThread().getName()+"取出"+queue.take());//如果空了则等待
                 }catch (InterruptedException e){
                     e.printStackTrace();

                 }
             }).start();
        }

    }
}
