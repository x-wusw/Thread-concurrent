package Reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrantlock(手动锁)替代synchornized
 * reentrantlock必须释放锁（经常在finally中释放锁）
 *
 *
 *
 */
public class Demo1 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();//相当于synchornized（this）
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }

    /**
     * 使用trylock尝试获取锁 根据锁的值来判断是否得到了锁
     * 可指定trylock的时间  由于trylock（time）抛出异常，所以要注意unlock的处理，必须放在finally中
     */

    void m2(){
        boolean b;
        try {
            b=lock.tryLock(5, TimeUnit.SECONDS);
            if(b){
                System.out.println("m2...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }
    public static void main(String[] args) {
        Demo1 d = new Demo1();
        new Thread(()->d.m1(),"t1").start();

        new Thread(()->d.m2(),"t2").start();
    }
}
