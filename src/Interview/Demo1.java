package Interview;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：创建一个固定容量的同步容器，有put和get方法，以及getcount方法
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 *
 * 使用wait（）和notify/notifyAll来实现
 *
 * 使用lock和condition实现 比上种方式更能精确的指定那些线程被唤醒
 */
public class Demo1<T> {
    final LinkedList<T> list = new LinkedList<>();
    final int max = 10;
    int count = 0;

    ReentrantLock r = new ReentrantLock();
    Condition producer = r.newCondition();
    Condition consumer = r.newCondition();

     void put(T t) {
        try {
            r.lock();
            while (list.size()==max){
                producer.await();
            }
            list.add(t);
            ++count;
            consumer.signalAll();//通知消费者
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            r.unlock();
        }
    }

     T get() {
        T t = null;
        try {
            r.lock();
            while (list.size()==0){
                consumer.await();
            }
            t = list.removeFirst();
            --count;
            producer.signalAll();//通知生产者
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            r.unlock();

        }
        return t;
    }

    public static void main(String[] args) {
        Demo1 d = new Demo1();
        //消费者先启动
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 2; j++) {
                    System.out.println(Thread.currentThread().getName()+"取出     "+d.get());
                }
            },"消费者"+i).start();
        }
        //生产者启动
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    d.put(Thread.currentThread().getName()+"  "+j);
                }
            },"生产者"+i).start();

        }

    }
}
