package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new SynchronousQueue<>();//容量为0
        new Thread(()->{
            try {
                System.out.println(queue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        //queue.add("aaa");//报异常
        queue.put("aaa");//等待消费者取出，或者一直阻塞
        System.out.println(queue.size());
    }
}
