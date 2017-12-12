package com.sac.swagger;

/**
 * Created by 99079 on 2017/9/21.
 */

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

@Configuration
@EnableSwagger2
public class SwaggerRestApiConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.sac.rest")).paths(PathSelectors.any())
                .build();
    }

    /**
     * 作者信息
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("eason", "18362090842@163.com", "www/githup.com/eason");
        return new ApiInfoBuilder().title("restfulAPi")
                .termsOfServiceUrl("http://baidu.com").contact(contact).version("0.01").build();
    }
}