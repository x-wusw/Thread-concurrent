package Thread3;
/*
synchronized同步代码块。锁对象随意
 */
public class Synchronized extends Thread {
    public static void main(String[] args) {
        Printer p = new Printer();

        new Thread(() -> {
            for (int i = 0; i <= 50; i++) {
               p.print1();
            }
        }).start();

        new Thread(){
            public void run() {
                for (int i = 0; i <= 50; i++) {
                    p.print2();
                }
            }
        }.start();
    }

}
class Printer {
    Demo1 d = new Demo1();//锁对象（d）可以为任何类
    public void print1() {
        synchronized (d) {
            System.out.println("湖");
            System.out.println("北");
            System.out.println("恩");
            System.out.println("施");
            System.out.println("\n");
        }
    }

    public void print2() {
        synchronized (d){
        System.out.println("湖");
        System.out.println("南");
        System.out.println("长");
        System.out.println("沙");
        System.out.println("\n");
        }
    }
}
