package cn.bearfang.forkjoin.test.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: technology-test
 * @description: 基于executor执行任务
 * @author: bearfang
 * @create: 2020-12-13 23:22
 **/
public class ExecutorDemo<T> {


    public List<T> invokeTask(List<String> parameters) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 1000,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>()
        );
        List<T> results = new ArrayList<>(parameters.size());
        parameters.forEach(parameter->{
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //执行调用外部的方法
                    T result= invokeExternalMethod(parameter);
                    //将返回的结果添加到结果集
                    //线程不安全问题，有几次调用就需要开启几个线程，无法感知到任务是否全部完成
                    results.add(result);
                }
            });
        });
        return results;
    }

    private T invokeExternalMethod(String parameter){
        //调用外部方法
        return null;
    }
}
