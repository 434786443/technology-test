package cn.bearfang.forkjoin.test.task;

import cn.bearfang.forkjoin.test.pojo.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 20:43
 **/
public class PrintDataTask extends RecursiveAction {

    private static final int LIST_SIZE = 10;

    /**
     * 处理的List
     */
    private List<ItemVO> itemVOS;

    public PrintDataTask(List<ItemVO> itemVOS) {
        this.itemVOS = itemVOS;
    }

    @Override
    protected void compute() {
        if(itemVOS.size()<=LIST_SIZE){
            itemVOS.forEach(itemVO->{
                //执行一些业务逻辑，比如插入数据库操作
                System.out.println(itemVO.toString());
            });
        }else{
            int size = itemVOS.size();
            //不断切分
            List<ItemVO> list1 = this.itemVOS.subList(0, size / 2);
            List<ItemVO> list2 = this.itemVOS.subList(size / 2, size);
            PrintDataTask printDataTask1 = new PrintDataTask(list1);
            PrintDataTask printDataTask2 = new PrintDataTask(list2);
            //执行任务  异步
            printDataTask1.fork();
            printDataTask2.fork();
            //阻塞等待结果
            printDataTask1.join();
            printDataTask2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ArrayList<ItemVO> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            ItemPO itemPO = new ItemPO();
            itemPO.setId(i);
            itemPO.setName(i + "号");
            itemPO.setSex("男");
            Cloth cloth = new Cloth();
            cloth.setColour(i + "色");
            cloth.setSize(i);
            Shoes shoes = new Shoes();
            shoes.setBrand("nike");
            shoes.setSize(i);
            ExtraItem extraItem = new ExtraItem();
            extraItem.setClothes(cloth);
            extraItem.setShoes(shoes);
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(itemPO,itemVO);
            BeanUtils.copyProperties(extraItem,itemVO);
            list.add(itemVO);
        }
        PrintDataTask printDataTask = new PrintDataTask(list);
        ForkJoinTask<Void> submit = forkJoinPool.submit(printDataTask);
        submit.invoke();
    }
}
