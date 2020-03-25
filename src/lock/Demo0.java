package lock;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile具有可见性但是不具有原子性
 */
public class Demo0 {

    volatile int count = 0;
    /*synchronized*/ void m1(){//加了synchronized结果为100 不加可出现任意数
        for(int i = 0;i<10;i++){
            count++;

        }
    }

    public static void main(String[] args) {
        Demo0 d = new Demo0();

        List<Thread> threads = new ArrayList();
        for(int i = 0;i<10;i++)
            threads.add(new Thread(() -> d.m1(), "thread" + i));//创建10个线程

        threads.forEach((o)->o.start());
        for (Thread o : threads) {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
        System.out.println(d.count);
    }


}
