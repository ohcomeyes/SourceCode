package com.boce.flcp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boce.flcp"))
                .paths(PathSelectors.any())
                .build();
//                .pathMapping("/api")
        //访问地址 swagger-ui.html
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("协同设计平台API")
                .description("首页地址：http://192.168.1.107：8080/index.html")
                .termsOfServiceUrl("http://192.168.1.107：8080/index.html")
                .contact("哈哈宝")
                .version("1.0")
                .build();
    }
}
