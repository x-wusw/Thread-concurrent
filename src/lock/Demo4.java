package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 面试题：创建一个容器，实现add，size两个方法
 * 两个线程 线程一添加10个元素到容器中，线程二实现监控元素的个数，并当容器有5个时提示并退出
 *使用Countdownlatch 门栓
 */
public class Demo4 {
    volatile List list  = new ArrayList();

    void add(Object o){
        list.add(o);
    }
    int size(){
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);//为1时是阻止，为0时是放开
        Demo4 d = new Demo4();

        new Thread(()->{
            System.out.println("t2开始");
            if(d.size()!=5){
                try {
                    latch.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
        },"t2").start();

        new Thread(()->{
            System.out.println("t1开始");
            for (int i = 0;i<10;i++){
                d.add(new Object());
                System.out.println("t1 "+i);
                if(d.size()==5){
                    latch.countDown();//打开门栓，使t2运行
                }
            }
            System.out.println("t1结束");
        },"t1").start();
    }
}
