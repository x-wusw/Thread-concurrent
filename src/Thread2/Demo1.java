package Thread2;


public class Demo1 {
    public static void main(String[] args){
        //匿名内部类实现线程启动
    new Thread(){
        public void run(){
            for(int i=0;i<=9;i++){
                System.out.println("0000");
            }
        }
    }.start();

    new Thread(() -> {
        for(int i=0;i<=9;i++){
            System.out.println("1111");
        }
    }).start();


 }
}