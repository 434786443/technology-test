package cn.bearfang.swagger.exception;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 22:36
 **/
public class BizException extends RuntimeException{

    public BizException(String s) {
        super(s);
    }
}
