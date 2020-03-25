package concurrent;
/**
 * 基于链接节点的无界TransferQueue
 */

import java.util.concurrent.LinkedTransferQueue;

public class Demo7 {
    public static void main(String[] args) throws Exception{
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        //开启消费者
        new Thread(()->{
            try {
                System.out.println(queue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        //生产者
        queue.transfer("aaa");//transfer 将元素转移给消费者，如有必要，等待(如果没有消费者会一直阻塞)
    }
}
