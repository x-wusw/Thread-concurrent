package Reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3 extends Thread {
    ReentrantLock r = new ReentrantLock(true);//参数为true 是公平锁（谁等的久谁先得到锁），效率低

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            r.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁"+i);
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                r.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        t1.start();
        t2.start();

    }

}
