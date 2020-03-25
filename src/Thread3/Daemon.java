package Thread3;
/*
    Daemon守护线程，当其他线程执行结束后再结束
 */
public class Daemon extends Thread {
    public static void main(String[] args){
        Thread t1 = new Thread(){
            public void run() {
                for (int i = 0;i<=2;i++) {
                    System.out.println(getName() + "11");
                }
            }
        };

        Thread t2 = new Thread(){
            public void run() {
                for (int j = 0; j <= 100; j++) {
                    System.out.println(getName() + "22");
                }
            }
        };
        t1.start();
        t2.start();
        t2.setDaemon(true);
    }
}
