package ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelComputing {//并行计算
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        List<Integer> result = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        ExecutorService service = Executors.newFixedThreadPool(4);//创建容器为4的线程池

        MyTask task1 = new MyTask(1,80000);
        MyTask task2 = new MyTask(800001,120000);
        MyTask task3 = new MyTask(120001,150000);
        MyTask task4 = new MyTask(150001,200000);
        List<Integer> integerList  = new ArrayList<>();
        integerList.addAll(task1.call());
        integerList.addAll(task2.call());
        integerList.addAll(task3.call());
        integerList.addAll(task4.call());
        Future<List<Integer>> f1 = service.submit(task1);
        Future<List<Integer>> f2 = service.submit(task2);
        Future<List<Integer>> f3 = service.submit(task3);
        Future<List<Integer>> f4 = service.submit(task4);
        System.out.println(integerList);
        long start1 = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        long end1 = System.currentTimeMillis();
        System.out.println(f1);

        System.out.println(end1-start1);

    }


    static class MyTask implements Callable<List<Integer>>{
        int startPos,endPos;
        MyTask(int s,int e){
            this.endPos = e;
            this.startPos = s;
        }
        @Override
        public List<Integer> call() throws Exception {
            List<Integer> list = getPrime(startPos,endPos);
            return list;
        }
    }

    static boolean isPrime(int num){//判断是否为质数
        for (int i = 2; i <= num/2; i++) {
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    static List<Integer> getPrime(int start,int end){//得到start到end之间的质数
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) results.add(i);
        }
        return results;
    }
}
