package cn.bearfang.forkjoin.test.pojo;

import lombok.Data;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 20:45
 **/
@Data
public class ItemVO {

    private int id;

    private String name;

    private int sex;

    private Cloth clothes;

    private Shoes shoes;

    @Override
    public String toString() {
        return "ItemVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", clothes=" + clothes +
                ", shoes=" + shoes +
                '}';
    }
}
