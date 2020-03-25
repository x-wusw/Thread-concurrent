package ThreadLocal;
/**
 * ThreadLocal是空间换时间  synchornized是时间换空间
 * ThreadLocal:用于实现线程内部的数据共享叫线程共享（对于同一个线程内部数据一致），即相同的一段代码 多个线程来执行
 * 每个线程使用的数据只与当前线程有关。
 *
 * 实现原理：ThreadLocal相当于一个map 当前线程存储当前的变量的时候 map.put(确定线程的唯一值（比如变量名称）,变量)
 * 然后获取的时候直接拿过来就行
 */

import java.util.concurrent.TimeUnit;

public class Demo {
    static ThreadLocal<person> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            threadLocal.set(new person());
            System.out.println(threadLocal.get());
        }).start();
    }
    static class person{
        String name = "张三";
    }
}
