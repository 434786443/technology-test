package cn.bearfang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2021-02-27 21:43
 **/
@SpringBootApplication
public class ToolApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ToolApplication.class);
        app.run(args);
    }
}
