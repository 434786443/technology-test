package cn.bearfang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2021-02-28 13:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;

    private Integer age;

    private String sex;
}
