package Interview;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：创建一个固定容量的同步容器，有put和get方法，以及getcount方法
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 *
 * 使用wait（）和notify/notifyAll来实现
 */
public class Demo0<T>{
    final LinkedList list = new LinkedList();
    final int max = 10;
    int count = 0;

    synchronized void put(T t){
        while(list.size()==max){//为啥不用if而用while 原因：在一个生产者线程生产一个元素时，用if会出现在唤醒到生产的别的生产者者线程会抢先生产一个的情况。
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
        this.notifyAll();//唤醒消费者线程  为什么不用notify，以为notify可能唤醒生产者
    }

    synchronized T get(){
        T t = null;
        while (list.size()==0){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        t = (T) list.removeFirst();
        --count;
        this.notifyAll();//唤醒生产者线程
        return t;
    }

    public static void main(String[] args) {
        Demo0 d = new Demo0();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 2; j++) {
                    System.out.println(Thread.currentThread().getName()+"get"+d.get());
                }

            },"消费者"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //启动生产者线程
        for (int i = 0; i < 2; i++) {

            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    d.put(Thread.currentThread().getName()+"  "+j);
                }
                System.out.println(d.count);
            },"生产者"+i).start();
        }
    }


}
