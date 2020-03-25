package lock;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题：创建一个容器，实现add，size两个方法
 * 两个线程 线程一添加10个元素到容器中，线程二实现监控元素的个数，并当容器有5个时提示并退出
 *缺点：线程二的死循环占用cpu资源
 */
public class Demo2 {

    volatile List list = new ArrayList();//若不加volatile，当有五个元素时线程二不会提示，加了会提示线程二

    void add(Object o){
        list.add(o);
    }
    int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                d.add(new Object());
                System.out.println("add"+i);
            }
        }
        ,"t1").start();

        new Thread(()->{
            while (true){
            if(d.list.size()==5){
                System.out.println("t2结束");
                break;
             }
            }
        },"t2").start();

    }
}
