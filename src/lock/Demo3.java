package lock;


import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：创建一个容器，实现add，size两个方法
 * 两个线程 线程一添加10个元素到容器中，线程二实现监控元素的个数，并当容器有5个时提示并退出
 *使用wait（），notify（），保证t2先执行 注意wait释放锁，notify不释放
 */
public class Demo3 {
    volatile List list = new ArrayList();

    void add(Object o){
        list.add(o);
    }

    int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3();
        Object lock = new Object();
        new Thread(()->{
            synchronized(lock) {
                System.out.println("t2开始");
                if (d.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2结束");
                    lock.notify();
                }
            }
        },"t2").start();


    new Thread(()->{
        synchronized(lock){
            System.out.println("t1开始");
        for (int i = 0;i<10;i++){
            d.add(new Object());
            System.out.println("t1"+i);
            if(d.size()==5){
                lock.notify();
                try {
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("t1结束");
    }},"t1").start();
    }
}
