package ThreadPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPool_1 extends ForkJoinPool {
    static long[] num = new long[1000000];
    static final int max = 50000;
    static Random r = new Random();
    static {
        long ss = System.currentTimeMillis();
        for (int i = 0; i < num.length; i++) {
            num[i] = r.nextInt(100);
        }
        long ee = System.currentTimeMillis();
        System.out.println("时间="+(ee-ss)+"毫秒");
        System.out.println(Arrays.stream(num).sum());
    }
    static class MyTask extends RecursiveAction {//递归无返回值
        int start,end;
        MyTask(int s,int t){
            this.start = s;
            this.end = t;
        }
        @Override
        protected void compute() {
            if(end-start<=max){
                long sum = 0L;
                for (int i = start; i <end; i++) {
                    sum+=num[i];
                }
                System.out.println("from "+start+" to "+end+" = "+sum);
            }
            else {
                int middle = start+(end-start)/2;
                MyTask task1 = new MyTask(start,middle);
                MyTask task2 = new MyTask(middle,end);
                task1.compute();
                task2.compute();
            }
        }
    }

    static class MyTask1 extends RecursiveTask<Long> {//递归有返回值
        int start,end;
        MyTask1(int s,int e){
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if(end - start<=max){
                for (int i = start; i <end ; i++) {
                    sum+=num[i];
                }
                return sum;
            }
            else {
                int middle = start + (end-start)/2;
                MyTask1 task = new MyTask1(start,middle);
                MyTask1 task1 = new MyTask1(middle,end);
                task.fork();
                task1.fork();
                return task.join()+task1.join();
            }
        }
    }
    public static void main(String[] args) throws Exception{
            ForkJoinPool fjp = new ForkJoinPool_1();
            MyTask1 task = new MyTask1(0,num.length);
            long ss1 = System.currentTimeMillis();
            fjp.execute(task);
            long result = task.join();
            long ee1 = System.currentTimeMillis();
            System.out.println("时间="+(ee1-ss1)+"毫秒");
            System.out.println(result);

            MyTask task1 = new MyTask(0,num.length);
            long ss2 = System.currentTimeMillis();
            fjp.execute(task1);
            //long result1 = task.join();
            long ee2 = System.currentTimeMillis();
            System.out.println("时间="+(ee2-ss2)+"毫秒");
            //System.out.println(result1);
            //System.in.read();//守护线程，主线程不阻塞，显示不出来



    }
}
