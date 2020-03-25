package lock;

import Thread3.Synchronized;

import java.util.concurrent.TimeUnit;

/**
 * synchronized优化
 * 同步代码块的内容越少越好
 * 比较m1，m2
 */
public class Demo1 {
    int count = 0;
    synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        count++;
    }

    void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        /**
         * 业务逻辑中只有下面代码需要加synchornized，这时不应该给整个方法加锁
         * 采用细粒度锁，减少线程争用时间，提高效率
         */
        synchronized (this){
            count++;
        }
    }

    public static void main(String[] args) {
        Demo1 d  = new Demo1();


        long start = System.currentTimeMillis();
        new Thread(()->d.m1()).start();
        long end = System.currentTimeMillis();

        long start1 = System.currentTimeMillis();
        new Thread(()->d.m2()).start();
        long end1 = System.currentTimeMillis();

        System.out.println(start-end);
        System.out.println(start1-end1);
        //System.out.println(d.count);
    }

}
