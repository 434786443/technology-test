package cn.bearfang.apo.test.controller;

import cn.bearfang.apo.test.annotation.LogAnnotation;
import cn.bearfang.apo.test.model.RequestObject;
import cn.bearfang.apo.test.model.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2020-11-14 23:03
 **/
@RestController
@RequestMapping(value = "/aop")
public class AopTestController {

    @RequestMapping(value="/log/aadvice/test",method = RequestMethod.POST, produces = "application/json;charset=GBK")
    //@LogAnnotation
    public ResponseObject logAdviceTest(ServletRequest servletRequest, ServletResponse response, @RequestBody RequestObject request, @RequestParam(name = "sign") String signg) throws UnsupportedEncodingException {
        //"{\"message\":\"SUCCESS\",\"code\":200,\"data\":" + request + "}"
        ObjectMapper objectMapper = new ObjectMapper();
        String s1 = new String(request.getArg1().getBytes(servletRequest.getCharacterEncoding()), servletRequest.getCharacterEncoding());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode("0");
        responseObject.setMessage("无消息".getBytes("UTF-8"));
        responseObject.setData(request.getArg1() + request.getArg2());
        try {
            String s = objectMapper.writeValueAsString(responseObject);
            System.out.println(signg);
            return responseObject;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseObject;
    }

    @RequestMapping(value="/log/aadvice/test2",method = RequestMethod.POST,consumes = "multipart/form-data", produces = "application/json;charset=GBK")
    //@LogAnnotation
    public ResponseObject logAdviceTest2(ServletRequest servletRequest, ServletResponse response, @RequestParam Map map) throws UnsupportedEncodingException {
        //"{\"message\":\"SUCCESS\",\"code\":200,\"data\":" + request + "}"
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        String sign = (String)map.get("signss");
        sign=new String(sign.getBytes(servletRequest.getCharacterEncoding()), "UTF-8");
        //String s1 = new String(request.getArg1().getBytes(servletRequest.getCharacterEncoding()), servletRequest.getCharacterEncoding());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode("0");
        responseObject.setMessage("无消息".getBytes("UTF-8"));
        //responseObject.setData(request.getArg1() + request.getArg2());
        try {
            String s = objectMapper.writeValueAsString(responseObject);
            //System.out.println(signg);
            return responseObject;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseObject;
    }


}
