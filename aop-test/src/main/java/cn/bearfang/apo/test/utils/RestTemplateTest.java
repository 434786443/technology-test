package cn.bearfang.apo.test.utils;

import cn.bearfang.apo.test.model.RequestObject;
import cn.bearfang.apo.test.model.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2021-01-16 17:25
 **/
public class RestTemplateTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        RequestObject requestObject = new RequestObject();
        requestObject.setArg1("ff");
        requestObject.setArg2("cc");
        try {
            String s = objectMapper.writeValueAsString(requestObject);
            String url = "http://127.0.0.1:8080/aop/log/aadvice/test?sign=123";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(s,httpHeaders);
            ResponseEntity<ResponseObject> exchange =
                    restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResponseObject.class);
            ResponseObject body = exchange.getBody();

            System.out.println(objectMapper.writeValueAsString(body));
        } catch (JsonProcessingException e) {
        }

    }
}
