package Reentrantlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantlock可以调用lockInterruotiablyf方法  可以对中断方法做出响应
 * 在一个线程等待锁的过程可以被打断
 */
public class Demo2 {
    public static void main(String[] args) {
        ReentrantLock r = new ReentrantLock();

        new Thread(() -> {
            try {
                r.lock();
                System.out.println("m1 start....");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("m1 end....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        },"m1").start();

        Thread m2 = new Thread(() -> {
            try {
               // r.lock();
                r.lockInterruptibly();//可以对中断做出响应
                System.out.println("m2 start....");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("m2 end....");
            } catch (InterruptedException e) {
                    System.out.println("中断。。。。");
            } finally {
                if(r.tryLock()){
                    r.unlock();
                }else {
                    System.out.println("未获取到锁");
                }
            }

        });
        m2.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        m2.interrupt();


    }
}