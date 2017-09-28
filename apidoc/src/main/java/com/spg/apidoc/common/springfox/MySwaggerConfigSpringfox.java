package com.spg.apidoc.common.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @description:
 * @author denghuifan
 * @create_time：2017年7月17日17:58:00
 * @version V1.0.0
 * http://localhost:9091/apidoc/swagger-ui.html
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
// Loads the spring beans required by the framework
@ComponentScan(basePackages = {"com.spg.apidoc.controller"})
public class MySwaggerConfigSpringfox extends WebMvcConfigurationSupport
{
	
	 @Bean
    public Docket customDocket() {
        //
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spg.apidoc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo()
    {
    	return new ApiInfoBuilder()  
                .title("Spring 中使用Swagger2构建RESTful APIs")  
                .termsOfServiceUrl("http://blog.csdn.net/he90227")  
                .contact("denghf")  
                .version("1.1")  
                .build();  
    }
}