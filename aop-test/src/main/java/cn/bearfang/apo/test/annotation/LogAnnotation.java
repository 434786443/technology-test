package cn.bearfang.apo.test.annotation;

import java.lang.annotation.*;

/**
 * @program: technology-test
 * @description
 * @author: bearfang
 * @create: 2020-11-01 22:50
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
}
