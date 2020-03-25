package Thread3;

public class join extends Thread {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i <= 50; i++) {
                    if(i%5==0){
                        Thread.yield();
                        System.out.println(getName()+"   :"+i);
                    }
                    System.out.println(getName() + "   11");
                }
            }
        };
      //join加入线程 yield礼让线程
        Thread t2 = new Thread() {
            public void run() {
                for (int j = 0; j <= 100; j++) {
                   /* if(j==10) {
                        try {
                            t1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }*/
                    System.out.println(getName() + "   22");
                }
            }
        };
       /*t1.setPriority(MIN_PRIORITY);
        t2.setPriority(MAX_PRIORITY);
        */
        //setPriority设置优先级，0-10  MAX_PRIORITY=10
        t1.start();
        t2.start();
    }
}