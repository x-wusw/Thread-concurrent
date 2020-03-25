package Thread4;

public class Ticket {
    public static void main(String[] args){
        new Tickets().start();
        new Tickets().start();
        new Tickets().start();
        new Tickets().start();

    }

}
class Tickets extends Thread{
    private static int tickets = 100;
    public void run() {
        while (true){
            synchronized (Tickets.class){
            if(tickets==0){
                break;
            }
            System.out.println(getName()+"....这是第"+tickets--+"票");
        }}
    }
}
