package Reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrantlock(手动锁)替代synchornized
 * reentrantlock必须释放锁（经常在finally中释放锁）
 *
 */
public class Demo0 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();//相当于synchornized（this）
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }

    void m2(){
        lock.lock();
        System.out.println("m2....");
        lock.unlock();
    }
    public static void main(String[] args) {
        Demo0 d = new Demo0();
        new Thread(()->d.m1(),"t1").start();

        new Thread(()->d.m2(),"t2").start();
    }
}
