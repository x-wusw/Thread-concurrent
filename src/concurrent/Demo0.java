package concurrent;

import java.util.Vector;

/**
 * 火车站卖票,有N张票，每张票都有编号
 */
public class Demo0 {

    static Vector<String> tickets = new Vector<>();//Vector线程安全
    static {
        for (int i = 0; i < 20; i++) {
            tickets.add("编号"+i);
        }
    }

    public static void main(String[] args) {
        /**
         * 判断操作分离
         * 问题：取票不按照顺序输出
         * 原因：size和remove操作都是原子操作，但是两个操作之间有可能插入别的线程即判断操作分离
         */
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                while (tickets.size()>0){
                    System.out.println("取出"+tickets.remove(0));
                }
            }).start();
        }
    }
}
