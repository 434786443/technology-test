package cn.bearfang.apo.test.model;

import lombok.Data;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2020-11-15 00:03
 **/
@Data
public class RequestObject {
    String arg1;
    String arg2;

    public RequestObject() {
    }

    public RequestObject(String arg1, String arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
