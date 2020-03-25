package concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Demo4 {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.offer(i+"a");//在该队列的尾部插入指定的元素
        }
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(queue.poll());//检索并删除此队列的头部，如果此队列为空，则返回 null 。
        System.out.println(queue.size());
        System.out.println(queue.peek());//检索但不删除此队列的头，如果此队列为空，则返回 null 。
        System.out.println(queue.size());

    }


}
