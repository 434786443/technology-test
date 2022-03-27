package cn.bearfang.apo.test.controller;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

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
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
