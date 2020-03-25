package ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(1000000);
        Random r = new Random();
        for (int i = 0; i < list.size(); i++) {
            list.add(r.nextInt(10000)+i);
        }
        long s = System.currentTimeMillis();
        list.forEach(o->isPrime(o));
        long e = System.currentTimeMillis();
        System.out.println("正常计算： "+(e-s)+"毫秒");


        long start = System.currentTimeMillis();
        list.parallelStream().forEach(num -> isPrime(num));
        long end = System.currentTimeMillis();
        System.out.println("parallelStream： "+(end-start)+"毫秒");


    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
