package concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 火车站卖票,有N张票，每张票都有编号
 */
public class Demo1 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();//ConcurrentLinkedQueue线程安全
    static {
        for (int i = 0; i < 20; i++) {
            tickets.add("编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if(s==null){
                        break;
                    }else {
                        System.out.println("取出票"+s);
                    }
                }
            }).start();
        }
    }

}
