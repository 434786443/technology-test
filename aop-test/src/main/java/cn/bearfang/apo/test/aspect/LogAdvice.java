package cn.bearfang.apo.test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 22:47
 **/
@Aspect
@Component
public class LogAdvice {

    @Pointcut("@annotation(cn.bearfang.apo.test.annotation.LogAnnotation)")
    private void logPrint(){}

    @Before("logPrint()")
    public void logPrintFunction(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        //执行切面操作
    }

}
