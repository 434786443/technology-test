package cn.bearfang.parallelstream.test;

import cn.bearfang.forkjoin.test.pojo.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 22:08
 **/
public class ParallelStreamDemo {

    public static void main(String[] args) {
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
        list.parallelStream().forEach(itemVO ->{
            System.out.println(itemVO.toString());
        });
    }
}
