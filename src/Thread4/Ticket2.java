package Thread4;

public class Ticket2 {
    public static void main(String args[]){
        Demo d = new Demo();
        new Thread(d).start();
        new Thread(d).start();
        new Thread(d).start();
        new Thread(d).start();

    }
}

class Demo implements Runnable{
    private  int tickets=100;
    public void run() {
        synchronized (this){
        while(true){
            if(tickets==0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"....这是第"+tickets--+"张票");
        }
     }
    }
}
