package Thread;

public class Demo2 {
    public static void main(String args[]){
        Runn r = new Runn(100);
        new Thread(r).start();
        new Thread(r).stop();

    }
    /*
    创建一个线程是声明实现类Runnable接口。
    那个类然后实现了run方法。 然后可以分配类的实例，
    在创建Thread时作为参数传递，并启动
     */
}
class Runn implements Runnable{
    int i;
    Runn(int i){
        this.i=i;
    }

    public void run() {
        for(int i=0;i<=9;i++){
            System.out.println("0000");
        }
    }
}
