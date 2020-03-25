package Thread;

public class Demo1  {
    public static void main(String args[]){
        Thread1 t1 = new Thread1();
        t1.start();

        for(int i=0;i<=9;i++){
            System.out.println("0000");
        }
    }

/*
    建一个新的执行线程有两种方法。
    一个是将一个类声明为Thread的子类。
    这个子类应该重写run类的方法Thread 。
    然后可以分配并启动子类的实例
 */
}
class Thread1 extends Thread{
    public void run(){
        for(int i=0;i<=9;i++){
            System.out.println("1111");
        }
    }

}
