package cn.bearfang.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-06-12 21:58
 **/
//@EnableResourceServer
@SpringBootApplication
public class TokenApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TokenApplication.class);
        app.run(args);
    }

}
