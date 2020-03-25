package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 write and copy  写效率低，读效率高 适合写少读多情景
 *CopyOnWriteArrayList
 */
public class Demo3 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        Thread[] threads = new Thread[100];
        Random r = new Random();
        long start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
                Runnable task = () -> {
                    for (int j = 0; j < 100; j++) {
                        list.add("a"+r.nextInt(10000));
                    }
                };
                threads[i] = new Thread(task);
        }

        Arrays.asList(threads).forEach(Thread::start);
        Arrays.asList(threads).forEach(thread -> {
            try {
                    thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        long end  = System.currentTimeMillis();

        System.out.println("运行时长:"+(end-start));
        System.out.println(list.size());

    }
}
