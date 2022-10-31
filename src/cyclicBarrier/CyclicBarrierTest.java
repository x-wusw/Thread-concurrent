package cyclicBarrier;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * @author shenw
 * @version 1.0
 * @date 2022/10/31 0031 12:48:24
 * @description 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
 * CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数量，每个线程调用await方法告CyclicBarrier我已经到达了屏障，
 * 然后当前线程被阻塞。示例代码如下所示：
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(1+new Date().toString());
            }
        }).start();
        try {
            c.await();
        } catch (Exception e) {
        }
        System.out.println(2+new Date().toString());
    }
}
