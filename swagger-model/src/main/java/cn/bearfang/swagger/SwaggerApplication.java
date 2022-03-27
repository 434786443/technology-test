package cn.bearfang.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-03-27 20:15
 **/
@SpringBootApplication
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SwaggerApplication.class);
        app.run(args);
    }
}
