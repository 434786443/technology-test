package cn.bearfang.forkjoin.test.task;

import java.util.concurrent.RecursiveTask;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 20:05
 **/
public class CountTask extends RecursiveTask<Integer> {

    private static final int TASK_SZIE = 10;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end - start <= TASK_SZIE){
            //执行任务
            int result = 0;
            for(int i=start;i<=end;++i){
                result += i;
            }
            return result;
        }else{
            int mid = (end+start) / 2;
            CountTask countTask1 = new CountTask(start, mid);
            CountTask countTask2 = new CountTask(mid+1, end);
            //执行子任务
            countTask1.fork();
            countTask2.fork();
            //获取子任务结果
            Integer result1 = countTask1.join();
            Integer result2 = countTask2.join();
            return result1 + result2;
        }
    }
}
