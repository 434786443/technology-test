package cn.bearfang.apo.test.controller;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-01-09 22:04
 **/
@Component
public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String contentType = request.getContentType();
        String characterEncoding = request.getCharacterEncoding();
        //request.setCharacterEncoding("GB2312");
        String contentType1 = response.getContentType();
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        //String decode = URLDecoder.decode(httpRequest.getRequestURL().toString().split("//?")[1], "UTF-8");
        //decode = decode;
        String basicAuth = new String(Base64.getDecoder().decode(httpRequest.getHeader("Authorization").split(" ")[1]));
        String[] split = basicAuth.split(":");
        String userName = split[0];
        String passWord = split[1];
        //todo 验证
        chain.doFilter(request, response);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode("%E7%88%B1%E4%BD%A0%E4%BD%A0%E5%A8%98", StandardCharsets.US_ASCII.name());
        System.out.println(decode);
    }

    @Override
    public void destroy() {

    }
}
