package cn.bearfang.swagger.controller;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 20:30
 **/

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "秘密花园门禁")
@RestController
@RequestMapping("/mimihuayuan")
public class HelloController{

    @ApiOperation("欢迎秘密花园")
    @GetMapping("/hello")
    public String hello(@ApiParam("名字")String name){
        return "hello, " + name;
    }


}