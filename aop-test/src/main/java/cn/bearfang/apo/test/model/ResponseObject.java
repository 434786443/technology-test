package cn.bearfang.apo.test.model;

import lombok.Data;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2020-11-14 23:45
 **/
@Data
public class ResponseObject {
    String code;
    String message;
    String data;
}
