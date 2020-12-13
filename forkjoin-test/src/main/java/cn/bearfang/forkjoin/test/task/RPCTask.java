package cn.bearfang.forkjoin.test.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2020-12-13 23:51
 **/
public class RPCTask<T,V> extends RecursiveTask<List<V>> {

    private static final int TASK_SZIE = 3;

    private List<T> paras;

    public RPCTask(List<T> paras){
        this.paras = paras;
    }

    public List<V> streamCompute() throws ExecutionException, InterruptedException {
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
//        int parallelism = 10;
        //设置线程数
//        List<V> result = new ForkJoinPool(parallelism).submit(() ->
//                paras.parallelStream().map(this::invokeExternalMethod).collect(Collectors.toList()))
//                .get();

        return paras.parallelStream().map(this::invokeExternalMethod).collect(Collectors.toList());
    }

    @Override
    protected List<V> compute() {
        if(paras.size() <= TASK_SZIE){
            List<V> results = new ArrayList<V>();
            //执行任务
            paras.forEach(para->
                    {
                        //执行任务
                        V result = invokeExternalMethod(para);
                        results.add(result);
                    }

            );
            return results;
        }else{
            int size = paras.size();
            //切分任务
            RPCTask<T, V> rpcTask1 = new RPCTask<>(paras.subList(0, size/2));
            RPCTask<T, V> rpcTask2 = new RPCTask<>(paras.subList(size/2, size));
            //执行子任务
            rpcTask1.fork();
            rpcTask2.fork();
            //获取子任务结果
            List<V> result1= rpcTask1.join();
            List<V> result2 = rpcTask2.join();
            result1.addAll(result2);
            return result1;
        }
    }

    private V invokeExternalMethod(T para){

        return null;
    }
}
