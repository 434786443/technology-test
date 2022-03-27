package cn.bearfang.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.bearfang.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("viboot-swagger2")	//标题
                .description("Restful-API-Doc")	//描述
                //.termsOfServiceUrl("https://www.cnblogs.com/viyoung") //这里配置的是服务网站，我写的是我的博客园站点~欢迎关注~
                .contact(new Contact("秘密花园", "http://localhost:8080/swagger-ui.html", "434.com"))
                .version("1.0") //版本
                .build();
    }
}
