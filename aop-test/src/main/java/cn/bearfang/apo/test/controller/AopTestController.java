package cn.bearfang.apo.test.controller;

import cn.bearfang.apo.test.annotation.LogAnnotation;
import cn.bearfang.apo.test.model.RequestObject;
import cn.bearfang.apo.test.model.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2020-11-14 23:03
 **/
@RestController
@RequestMapping(value = "/aop")
public class AopTestController {

    @RequestMapping(value="/log/aadvice/test",method = RequestMethod.POST)
    //@LogAnnotation
    public String logAdviceTest(@RequestBody RequestObject request,@RequestParam(name = "sign") String signg){
        //"{\"message\":\"SUCCESS\",\"code\":200,\"data\":" + request + "}"
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode("0");
        responseObject.setMessage("noMessage");
        responseObject.setData(request.getArg1() + request.getArg2());
        try {
            String s = objectMapper.writeValueAsString(responseObject);
            System.out.println(signg);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


}
