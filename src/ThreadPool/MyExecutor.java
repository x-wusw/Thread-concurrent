package ThreadPool;

import java.util.concurrent.Executor;

public class MyExecutor implements Executor {
    public static void main(String[] args) {
        new MyExecutor().execute(()->System.out.println("HELLO WORLD!"));
    }
    @Override
    public void execute(Runnable command) {
        //new Thread(command).run();
        command.run();
    }
}
