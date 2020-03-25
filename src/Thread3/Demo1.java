package Thread3;

public class Demo1 {
    public static void main(String[] args){
        new Thread(){
            public void run(){
                    System.out.println(currentThread().getName()+"0000");
            }
        }.start();

        new Thread(new Runnable(){
            public void run(){
                    System.out.println(Thread.currentThread().getName()+"1111");
            }
        }).start();
        Thread.currentThread().setName("xiaowu");
        //setname设置线程名；getName获取；currentThread()获取当前线程
        System.out.println(Thread.currentThread().getName());

    }
}