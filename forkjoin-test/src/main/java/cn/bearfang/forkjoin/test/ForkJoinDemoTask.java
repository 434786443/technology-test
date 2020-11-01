package cn.bearfang.forkjoin.test;

import cn.bearfang.forkjoin.test.task.CountTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 20:04
 **/
public class ForkJoinDemoTask {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 99);
        ForkJoinTask<Integer> result = forkJoinPool.submit(countTask);
        try {
            Integer integer = result.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
