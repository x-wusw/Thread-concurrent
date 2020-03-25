package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo6 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            queue.put(i+"a");//满了就会阻塞
        }
        /*
        queue.add("aaa");//抛出异常
        queue.offer("aaa");//没有异常，返回一个布尔值，false添加失败
        */
        boolean b = queue.offer("aaa",1, TimeUnit.SECONDS);//延时1秒添加
        System.out.println(b);
        System.out.println(queue);
    }
}
