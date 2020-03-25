package concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 比较各种同步容器的效率
 * 在多线程下ConcurrentHashMap比Hashtable快,原因：ConcurrentHashMap 锁细化
 */
public class Demo2 {

    public static void main(String[] args) {
        Map<String,String> map = new Hashtable<>();//运行时长：371
        //Map<String,String> map = new HashMap<>();//运行时长：303
        //Map<String,String> map = new ConcurrentHashMap<>();//运行时长:329 适合场景：高并发
        //Map<String,String> map = new ConcurrentSkipListMap<>();//运行时长:340 适合场景：高并发并且排序，插入效率低
        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch count = new CountDownLatch(threads.length);//100个门栓

        long start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    map.put(r.nextInt(1000)+"a",r.nextInt(1000)+"a");
                }
                count.countDown();//门栓减一
            });
        }

        Arrays.asList(threads).forEach(Thread::start);
        try {
            count.await();
        }catch (InterruptedException e){
            e.printStackTrace();

        }


        long end = System.currentTimeMillis();
        System.out.println("运行时长："+(end-start));
    }
}
